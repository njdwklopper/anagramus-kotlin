using Firebase.Auth;

namespace Anagramus.service._Base.session
{
    public interface IFirebaseHandler
    {
        bool IsUserNotNull();
        FirebaseAuth GetAuth();
        void SignOut();
    }
}