using Foundation;
using Google.SignIn;
using UIKit;

namespace Anagramus.iOS.service._base.session
{
    public class FirebaseIOSUserHandler :  UIViewController, ISignInDelegate, ISignInUIDelegate
    {
        public void DidSignIn(SignIn signIn, GoogleUser user, NSError error)
        {
            throw new System.NotImplementedException();
        }
    }
}