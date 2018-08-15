package com.app.leo.androideatitserver.Service;

import com.app.leo.androideatitserver.Common.Common;
import com.app.leo.androideatitserver.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String  tokenRefresh = FirebaseInstanceId.getInstance().getToken();
        updateToServer(tokenRefresh);
    }
    private void updateToServer(String tokenRefresh) {
        FirebaseDatabase db= FirebaseDatabase.getInstance();
        DatabaseReference tokenRef= db.getReference("Tokens");

        Token token=new Token(tokenRefresh,true);
        tokenRef.child(Common.currentUser.getPhone()).setValue(token);

    }
}
