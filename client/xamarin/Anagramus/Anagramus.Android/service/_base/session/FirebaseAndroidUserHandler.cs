using System;
using Anagramus.service._Base.session;
using Firebase.Auth;

namespace Anagramus.Android.service._base.session
{
    public class FirebaseAndroidUserHandler : IFirebaseHandler<FirebaseAuth>
    {

        private FirebaseAuth _auth = FirebaseAuth.Instance;
        
        public bool IsUserNotNull()
        {
            return false; //_auth.User != null;
        }

        public FirebaseAuth GetAuth()
        {
            throw new NotImplementedException();
        }

        public void SignOut()
        {
        }
    }
}