using Firebase.Auth;

namespace Anagramus.service._Base.session
{
    public class FirebaseUserHandler : IFirebaseHandler
    {
        private FirebaseAuth _auth = new FirebaseAuth();

        public bool IsUserNotNull()
        {
            return _auth.User != null;
        }

        public FirebaseAuth GetAuth()
        {
            return _auth;
        }

        public void SignOut()
        {
            _auth = null;
        }
    }
}