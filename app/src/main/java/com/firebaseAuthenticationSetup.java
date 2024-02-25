package com;

public class firebaseAuthenticationSetup {

}

//get the shared instance of the FirebaseAuth object
private FirebaseAuth mAuth {
    mAuth = FirebaseAuth.getInstance();
}

//check to see if user is currently signed in when initializing Activity
@Override
public void onStart() {
    super.onStart();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    updateUI(currentUser);
}

//take custom token from authentication server, pass it to signInWithCustomToken to sign in the user
mAuth.signInWithCustomToken(mCustomToken).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    if (task.isSuccessful()) {
        // Sign in success, update UI with the signed-in user's information
        Log.d(TAG, "signInWithCustomToken:success");
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
        } else {
            //If sign in fails, display a message to the user
            Log.w(TAG, "signInWithCustomToken:failure", task.getException())
            Toast.makeText(CustomAuthActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
            updateUI(null);
        }
    }
);

//to sign out a user
FirebaseAuth.getInstance().signOut();