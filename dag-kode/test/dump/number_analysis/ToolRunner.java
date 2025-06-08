package dump.number_analysis;

import dump.number_analysis.assessment.NumberUsageAssessment;
import dump.number_analysis.filter.ReferenceDataYYYFilter;
import dump.number_analysis.tools.EliminationTool;
import dump.number_analysis.tools.ReferenceDataAnalyser;
import org.junit.Test;

import java.util.Set;

import static dump.number_analysis.PastNumbers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ToolRunner {

    @Test
    public void eliminate() {
        new EliminationTool(PREVIOUS_DRAWS_LIST, RECENT_DRAW).eliminateFromAllCandidates(47);
    }//End Method

    @Test
    public void analysePastDraws() {
        new ReferenceDataAnalyser().analysePrevious(PREVIOUS_DRAWS_SET);
    }

    @Test
    public void popularNumbers(){
        NumberUsageAssessment ass = new NumberUsageAssessment(PREVIOUS_DRAWS_LIST.subList(0, 10));
        for (int i = 1; i < 15; i++) {
            double highAvg = ass.findAverageUseOfStrongNumbers(i);
            double lowAvg = ass.findAverageUseOfWeakNumbers(i);
            System.out.printf("[%d] - Avg High: %f, Avg Low: %f\n", i, highAvg, lowAvg);
        }
    }

    @Test
    public void should(){
        NumberUsageAssessment ass = new NumberUsageAssessment(PREVIOUS_DRAWS_SET);
        ReferenceDataYYYFilter yyy = new ReferenceDataYYYFilter(PREVIOUS_DRAWS_LIST);

        int total = 0;
        for (int i = 0; i < PREVIOUS_DRAWS_SET.size(); i++) {
            Set<Integer> combo = PREVIOUS_DRAWS_LIST.get(i);
            int sum = yyy.sum(combo);
            System.out.printf("Combo [%s]: score [%d]\n", combo, sum);

            total += sum;
        }

        int avg = total / PREVIOUS_DRAWS_LIST.size();

        new EliminationTool(PREVIOUS_DRAWS_LIST, RECENT_DRAW).eliminateFromAllCandidates(47);
    }

}//End Class
