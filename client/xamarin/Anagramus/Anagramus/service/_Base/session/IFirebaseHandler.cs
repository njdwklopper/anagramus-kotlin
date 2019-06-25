namespace Anagramus.service._Base.session
{
    public interface IFirebaseHandler<T>
    {
        bool IsUserNotNull();
        T GetAuth();
        void SignOut();
    }
}