package com.bdd.dou.task.runner;

import com.bdd.dou.task.steps.MainPageSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import com.bdd.dou.task.steps.BaseSteps;

import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Class is an entry point for executing BDD tests with JBehave.
 * So called main class.
 * <p>
 * To run from IDE (for Intelij IDEA):
 * 1. Create new JUnit configuration -> Select Runner class as the main.
 * 2. Int VM options specify filter in format -DmetaFilter="groovy: metaTag"
 * For current implementation: metaTag value is "debug" by default.
 * <p>
 * So VM Options values are equal to expression:
 * -DmetaFilter="groovy: debug"
 */
public class Runner extends JUnitStories {

    /**
     * Basic Jbehave configuration method.
     * Responsible for loading stories and reporters usage and report formatting.
     *
     * @return fully configured JBehave configuration object, that used for internal JBehave needs
     */
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(getClass().getClassLoader()))
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.XML, Format.HTML, Format.STATS));
    }

    /**
     * Finds com.bdd.dou.task.steps and extracting the from classes. Simplest implementation.
     * Each time you are adding new Steps class to project you should add it's object here.
     *
     * @return InjectableStepsFactory object to candidate JBehave com.bdd.dou.task.steps (process is internal).
     */
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                //add new *Steps objects here
                new MainPageSteps()
        );
    }

    /**
     * Returns list of paths where *.story files will be searched.
     *
     * @return list of *.story paths
     */
    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        URL codeLocations = CodeLocations.codeLocationFromClass(this.getClass());
        return finder.findPaths(codeLocations, "**/*.story", "");
    }

    /**
     * Embedder is overridden to use meta filter feature.
     * Meta filters allows you to run tests marked with metadata tags.
     *
     * @return fully configured JBehave Embedder object
     */
    @Override
    public Embedder configuredEmbedder() {
        Embedder embedder = super.configuredEmbedder();
        embedder.useMetaFilters(getMetaFilters());
        embedder.embedderControls().doGenerateViewAfterStories(true);
        return embedder;
    }

    /**
     * Returns list on meta filters that specified in run arguments.
     * To specify metafilters in IDE just enter this info in VM options (if you are using Intelij IDEA).
     *
     * @return list of meta filters to select scenarios for run from *.story files.
     */
    private List<String> getMetaFilters() {
        return Collections.singletonList(System.getProperty("metaFilter", "groovy: debug"));
    }

}
