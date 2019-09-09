package com.su.mobiledatausage.di;

import com.su.mobiledatausage.service.DataService;
import com.su.mobiledatausage.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(DataService service);

    void inject(ListViewModel viewModel);
}
