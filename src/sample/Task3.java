package sample;

public class Task3 {

    private static final int BILLION = 1000000000;
    private double money;
    private String moneyString ="";

    public Task3(double money) {
        this.money = money;
    }

    public String moneyToString (){
        boolean d= false;

        int dollars = (int) money;
        if (dollars>0)  d = true;
        int cents = (int) ((money - (int) money)*100);

        if ((dollars>BILLION) | (money<0))
            moneyString = "Не корректное число \n(число не должно превышать 1 000 000 000 и быть меньше 0)";
        else {
            if (dollars == BILLION)
                moneyString = "One billion dollars";
            else if (dollars == 1) {
                moneyString = "One dollar";
            }
            else  {
                if(dollars>999999) {
                    moneyString = getNumberOfHundred((int)dollars/1000000) + " million ";
                    dollars = dollars%1000000;
                }
                if (dollars>999){
                    moneyString = moneyString + getNumberOfHundred((int)dollars/1000) + " thousand ";
                    dollars = dollars%1000;
                }
                if (dollars>0) {
                    moneyString = moneyString + getNumberOfHundred(dollars);
                }
                if(d){
                    moneyString = moneyString +  " dollars ";
                }
            }
            if (cents>0)
                moneyString = moneyString + getNumberOfHundred(cents) + " cents";
        }

        return moneyString;
    }

    private String getNumberOfHundred (int n) {
        String s = "";
        if (n>999) return "ERROR";
        else {
            if ((n/100) > 0 ) { s = getStringNumber((int) n/100) + " hundred ";
            }
            if ((n%100)>20){
                s = s + getStringNumber( (n%100)-((n%100)%10));
                if ((n%100)%10>0) s = s + "-" + getStringNumber( (n%100)%10);
            }
            else s = s + getStringNumber((int) (n%100));
        }

        return s;

    }


    private String getStringNumber (int n) {
        switch (n){
            case 0:
                return "";
            case 1:
                return"one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return"ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 13:
                return "thirteen";
            case 14:
                return "fourteen";
            case 15:
                return "fifteen";
            case 16:
                return "sixteen";
            case 17:
                return "seventeen";
            case 18:
                return "eighteen";
            case 19:
                return "nineteen";
            case 20:
                return "twenty";
            case 30:
                return "thirty";
            case 40:
                return "forty";
            case 50:
                return "fifty";
            case 60:
                return "sixty";
            case 70:
                return "seventy";
            case 80:
                return "eighty";
            case 90:
                return "ninety";
            default: return "";
        }
    }
}

