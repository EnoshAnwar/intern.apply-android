package intern.apply.internapply;


import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.List;

import intern.apply.internapply.api.InternAPI;
import intern.apply.internapply.model.Job;
import intern.apply.internapply.model.JobBuilder;
import intern.apply.internapply.view.viewjobactivity.ViewJobActivity;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewJobAcceptanceTests extends ActivityInstrumentationTestCase2<ViewJobActivity> {
    private static final String ACTIVITY_ERROR = "wrong activity";
    private static final String TEXT_NOT_FOUND = "text not found";

    private Solo solo;
    private final InternAPI api;
    private String[] jobData;
    private ArrayList<Job> job;

    public ViewJobAcceptanceTests() {
        super(ViewJobActivity.class);
        api = mock(InternAPI.class);
        createFakeJob();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityIntent(new Intent().putExtra("TEST", true));
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testJobShowing() {
        solo.assertCurrentActivity(ACTIVITY_ERROR, ViewJobActivity.class);
        solo.waitForActivity(ViewJobActivity.class);

        Observable<List<Job>> output = Observable.fromArray(job);
        when(api.getJob(anyInt())).thenReturn(output);
        getActivity().setApi(api);

        solo.waitForView(R.id.jobView);
        findStrings(jobData);
    }

    private void createFakeJob() {
        job = new ArrayList<>();
        jobData = new String[]{
                "organization",
                "title",
                "location",
                "description"
        };

        job.add(new JobBuilder().setOrganization(jobData[0]).setTitle(jobData[1]).setLocation(jobData[2]).setDescription(jobData[3]).createJob());
    }

    private void findStrings(String[] expectedStrings) {
        for (String s : expectedStrings) {
            assertTrue(TEXT_NOT_FOUND, solo.waitForText(s));
        }
    }
}