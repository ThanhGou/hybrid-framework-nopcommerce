package pageUIs;

public class HomePageUI {
    //1 - public: ko set protected/ private/ default vi cac class khac ben ngoai package ko ke thua dc (class nay ko dung de ke thua vi chi dung cho homepage)
    //2 - static: de truy cap tu pham vi class khac ma khong can thong qua khoi tao object. E.g.
    // public class HomePageObject {
    //    public void clickToRegisterLink(){
    //        System.out.println(HomePageUI.REGISTER_LINK);
    //    }
    //}
    //3 - final: ko dc thay doi gia tri cua bien khi su dung
    public static final String REGISTER_LINK = "//a[@class='ico-register']";
    public static final String LOGIN_LINK = "//a[@class='ico-login']";
    public static final String ACCOUNT_LINK = "//a[@class='ico-acccount']";
}
