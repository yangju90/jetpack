package com.newegg.logistics.data;

import androidx.lifecycle.MutableLiveData;

import com.newegg.logistics.base.BaseViewModel;
import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.model.request.login.LoginRequestInfo;
import com.newegg.logistics.net.AccountUserDataSource;
import com.newegg.logistics.net.base.BaseRepository;

import retrofit2.Call;
import retrofit2.Response;


public class LoginRepository extends BaseRepository<AccountUserDataSource> {


    private LoginRepository(AccountUserDataSource dataSource, BaseViewModel baseViewModel) {
        super(dataSource, baseViewModel);
    }

    public static LoginRepository getInstance(BaseViewModel baseViewModel) {
        return new LoginRepository(new AccountUserDataSource(), baseViewModel);
    }

    public AccountUserResponseInfo loginSync(LoginRequestInfo loginRequestInfo) {
        Response<AccountUserResponseInfo> response = remoteDataSource.loginSync(loginRequestInfo);
        return response.body();
    }


    public MutableLiveData<AccountUserResponseInfo> login(LoginRequestInfo loginRequestInfo) {
        MutableLiveData<AccountUserResponseInfo> result = new MutableLiveData<>();

        remoteDataSource.login(loginRequestInfo, callbackFactory.create((Call<AccountUserResponseInfo> call, Response<AccountUserResponseInfo> response) -> {
            result.setValue(response.body());
        }));

        return result;
    }

}