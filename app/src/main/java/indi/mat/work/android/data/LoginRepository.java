package indi.mat.work.android.data;


import androidx.lifecycle.MutableLiveData;

import indi.mat.work.android.base.BaseViewModel;
import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import indi.mat.work.android.net.AccountUserDataSource;
import indi.mat.work.android.net.base.BaseRepository;
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