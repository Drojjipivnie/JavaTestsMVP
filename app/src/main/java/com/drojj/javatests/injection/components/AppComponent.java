package com.drojj.javatests.injection.components;

import android.content.res.Resources;

import com.drojj.javatests.database.DatabaseInstaller;
import com.drojj.javatests.database.base.BaseDAO;
import com.drojj.javatests.injection.modules.AppModule;
import com.drojj.javatests.presentation.presenter.PasswordReminderDialogPresenter;
import com.drojj.javatests.presentation.presenter.auth.SignInPresenter;
import com.drojj.javatests.presentation.presenter.auth.SignUpPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    Resources getResources();

    void inject(SignInPresenter presenter);

    void inject(SignUpPresenter presenter);

    void inject(PasswordReminderDialogPresenter presenter);

    void inject(DatabaseInstaller databaseInstaller);

    void inject(BaseDAO dao);
}
