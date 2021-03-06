package intern.apply.internapply.SystemTests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * SystemTests
 * Contains all System tests.
 * Handles automatic running of all system tests.
 */

public class SystemTests {
    public static Test suite() {
        TestSuite suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(AddJobSystemTest.class);
        suite.addTestSuite(AddSalarySystemTest.class);
        suite.addTestSuite(JobListSystemTest.class);
        suite.addTestSuite(ContactUsSystemTest.class);
        suite.addTestSuite(JobCommentsSystemTest.class);
        suite.addTestSuite(AddCommentSystemTest.class);
        suite.addTestSuite(QuestionSystemTest.class);
        suite.addTestSuite(SearchBarSystemTest.class);
        suite.addTestSuite(SurveySystemTest.class);
        suite.addTestSuite(ViewJobSystemTest.class);
        suite.addTestSuite(JobRatingSystemTest.class);
        suite.addTestSuite(AnswersSystemTest.class);
        return suite;
    }
}
