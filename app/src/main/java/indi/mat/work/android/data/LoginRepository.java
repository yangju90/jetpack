package indi.mat.work.android.data;

import androidx.lifecycle.MutableLiveData;


import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import indi.mat.work.android.net.AccountUserDataSource;
import indi.mat.work.android.net.base.BaseRepository;
import retrofit2.Call;
import retrofit2.Response;


public class LoginRepository extends BaseRepository<AccountUserDataSource> {


    private LoginRepository(AccountUserDataSource dataSource) {
        super(dataSource);
    }

    public static LoginRepository getInstance() {
        return new LoginRepository(new AccountUserDataSource());
    }

    public MutableLiveData<AccountUserResponseInfo> login(LoginRequestInfo loginRequestInfo) {
        MutableLiveData<AccountUserResponseInfo> result = new MutableLiveData<>();
        remoteDataSource.login(loginRequestInfo, callbackFactory.create((Call<AccountUserResponseInfo> call, Response<AccountUserResponseInfo> response) -> {
            result.setValue(response.body());
        }));

        return result;
    }
}