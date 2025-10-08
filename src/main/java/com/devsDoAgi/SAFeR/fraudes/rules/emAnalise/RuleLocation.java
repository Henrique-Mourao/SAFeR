package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;
import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleLocation implements FraudRule {

    @Autowired
    TransacaoRepository transacaoRepository;
    SpatialContext ctx = SpatialContext.GEO;

    private Double calcDistanceInKm(Transacao currentTrasaction, Transacao lastTransaction) { //A transação anterior será inserida no construtor diretamente, a validaçção do get será feita no generalista da classe

        Double currentlongitude = currentTrasaction.getLocal()[0];
        Double currentLatitude = currentTrasaction.getLocal()[1];

        Double lastLongitude = lastTransaction.getLocal()[0];
        Double lastLatitude = lastTransaction.getLocal()[1];

        //Criação de objetos para calculo de distancia entre pontos
        Point currentLocation = ctx.getShapeFactory().pointXY(currentlongitude, currentLatitude);
        Point lastLocation = ctx.getShapeFactory().pointXY(lastLongitude,lastLatitude);

        //Faz o calculo da distancia dos dois pontos em graus e depois utiliza a constante DEG_TO_KM para converção em KM
        Double distance = ctx.calcDistance(currentLocation, lastLocation) * DistanceUtils.DEG_TO_KM;

        return distance;
    }

    //Metodo que calcula o tempo minimo aceitável entre transações em diferentes localidades
    private Duration calcMinimumTime(Double distanceInKm) {

        double hours = distanceInKm / 800.0; // velocidade média de um voo comercial
        int hourPart = (int) hours;
        int minutePart = (int) ((hours - hourPart) * 60);

        return Duration.ofHours(hourPart).plusMinutes(minutePart);

    }

    //Calcula o tempo entre ultima transação e atual
    private Duration calcPeriod(Transacao currentTransaction, Transacao lastTransaction) {
        return Duration.between(lastTransaction.getDataHoraOperacao(), currentTransaction.getDataHoraOperacao());
    }

    @Override
    public FraudResult evaluate(Transacao currentTransaction) {

        Duration maxAcceptablePeriod = Duration.ofHours(6); //Determina o periodo maximo entre transações para ativação da regra
        Optional<Transacao> lastTransactionOptional = transacaoRepository.getLastTransaction();

        if (lastTransactionOptional.isPresent()) { //Verifica se há transações anteriores

            Transacao lastTransaction = lastTransactionOptional.get();

            Double currentlongitude = currentTransaction.getLocal()[0];
            Double currentLatitude = currentTransaction.getLocal()[1];

            Double lastLongitude = lastTransaction.getLocal()[0];
            Double lastLatitude = lastTransaction.getLocal()[1];


            Duration periodBtwTransaction = calcPeriod(currentTransaction, lastTransaction);


            //Compara a duração entre transações com o ideal
            //Se o resultado for negativo ou igual a zero signififica que o periodo entre transações é valido para ativação da regra
            if (periodBtwTransaction.compareTo(maxAcceptablePeriod) <= 0
                    && currentlongitude != lastLongitude
                    && currentLatitude != lastLatitude) { //Verifica se transações acontecem em locais diferentes

                Double distanceInKm = calcDistanceInKm(currentTransaction, lastTransaction);  //Calculo da distancia entre as duas localizações
                Duration minimumTime = calcMinimumTime(distanceInKm);

                //Se resultado for negativo, a transação é considerada impossivel
                if (periodBtwTransaction.compareTo(minimumTime) <= 0) {
                    return new FraudResult("Regra de localização", 25); //Alta chance de ser fraude
                }

            }
        }
        return new FraudResult("Regra de localização", 0);
    }}