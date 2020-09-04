package com.multiaddress.transactions.ui;

import android.view.View;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewFinder;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Matcher;

import java.lang.reflect.Field;

import static androidx.test.espresso.Espresso.onView;

// Taken from https://stackoverflow.com/questions/50628219/is-it-possible-to-use-espressos-idlingresource-to-wait-until-a-certain-view-app

/**
 * We create our custom IdlingResource to get a callback when the view is shown.
 * We need this in case of views that aren't displayed right away (like, a list of transactions taken from API call).
 */
public class ViewShowIdlingResource implements IdlingResource {

  private static final String TAG = ViewShowIdlingResource.class.getSimpleName();

  private final Matcher<View> viewMatcher;
  private ResourceCallback resourceCallback;

  public ViewShowIdlingResource(final Matcher<View> viewMatcher) {
    this.viewMatcher = viewMatcher;
  }

  @Override
  public boolean isIdleNow() {
    View view = getView(viewMatcher);
    boolean idle = view == null || view.isShown();

    if (idle && resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }

    return idle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  @Override
  public String getName() {
    return this + viewMatcher.toString();
  }

  private static View getView(Matcher<View> viewMatcher) {
    try {
      ViewInteraction viewInteraction = onView(viewMatcher);
      Field finderField = viewInteraction.getClass().getDeclaredField("viewFinder");
      finderField.setAccessible(true);
      ViewFinder finder = (ViewFinder) finderField.get(viewInteraction);
      return finder.getView();
    } catch (Exception e) {
      return null;
    }
  }
}
