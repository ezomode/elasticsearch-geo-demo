package controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.mvc.Result;
import play.test.Helpers;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HomeControllerFunctionalTest {

	@Inject
	private Application application;

	@Before
	public void setup() {
		Module testModule = new AbstractModule() {
			@Override
			public void configure() {
				// Install custom test binding here
			}
		};

		GuiceApplicationBuilder builder = new GuiceApplicationLoader()
						.builder(new ApplicationLoader.Context(Environment.simple()))
						.overrides(testModule);
		Guice.createInjector(builder.applicationModule()).injectMembers(this);

		Helpers.start(application);
	}

	@After
	public void teardown() {
		Helpers.stop(application);
	}

	@Test
	public void welcomePage() {
		Result result = Helpers.route(application, controllers.routes.HomeController.index());

		assertThat(result.status(), equalTo(Helpers.OK));
	}
}

