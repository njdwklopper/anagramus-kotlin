using System;
using System.Linq;
using Xamarin.Forms;

namespace Anagramus
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private void Button_OnClicked(object sender, EventArgs e)
        {
            foreach (var var in Enumerable.Range(0, 10))
            {
                Console.WriteLine(var);
            }

            LabelOne.Text = "Button clicked!";
        }
    }
}