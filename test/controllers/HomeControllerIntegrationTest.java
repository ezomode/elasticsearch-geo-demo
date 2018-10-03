package controllers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class HomeControllerIntegrationTest {

	@Test
	public void testIntegration() {

		running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, browser -> {

			browser.goTo("http://localhost:3333");

			assertThat(browser.pageSource(), equalTo("{\"title\":\"elasticsearch geo index demo\",\"version\":\"1\"}"));

		});
	}
}
