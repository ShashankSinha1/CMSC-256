package cmsc256;

public class RamString implements WackyStringInterface {
    private String regString;

    public RamString(){
        regString = "CS@VCU!";
    }

    public RamString(String regString) throws IllegalArgumentException {
        if (regString == null){
            throw new IllegalArgumentException ("Invalid string");
        }
        this.regString = regString;
    }

    public String getEveryThirdCharacter() {
        String temp = "";
        for (int i = 0; i < regString.length(); i++){
            if (i % 3 == 2){
                temp += (regString.charAt(i));
            }
        }
        return temp;
    }

    public String getEvenOrOddCharacters(String evenOrOdd){
        String temp = "";

        if (evenOrOdd != "even" && evenOrOdd != "odd"){
            throw new IllegalArgumentException("Invalid input");
        }

        if (evenOrOdd == "even"){
            for (int i = 0; i < getWackyString().length(); i++){
                if ((i+1) % 2 == 0){
                    temp += regString.charAt(i);                }
            }
        }
        else if (evenOrOdd == "odd"){
            for (int i = 0; i < getWackyString().length(); i++){
                if((i+1) % 2 == 1){
                    temp += regString.charAt(i);
                }
            }
        }
        return temp;
    }
    public int countDoubleDigits(){
        int count = 0;
        for (int i = 0; i<regString.length()-2; i++)
        {
            if (regString.charAt(i) == regString.charAt(i+1) && regString.charAt(i) != regString.charAt(i+2) &&
                    (Character.isDigit (regString.charAt(i+2)) == false) && Character.isDigit (regString.charAt(i)) == true) {
                if (i>0 && Character.isDigit (regString.charAt(i-1)) == false)
                    count++;
                if (i==0)
                    count++;
            }
        }
        if (regString.length()>3)
            if (regString.charAt (regString.length()-1) == regString.charAt (regString.length()-2) &&
                    Character.isDigit (regString.charAt (regString.length()-3)) == false){
                count++;
            }
        return count;
    }

    public boolean isValidVCUEmail() {
        if (regString.indexOf('@') != 0 && (regString.contains("@vcu.edu") || regString.contains("@mymail.vcu.edu"))){
            return true;
        } else {
            return false;
        }
    }

    public String standardizePhoneNumber() {
        String phone;
        String number = "";
        int i = 0;
        while(i < regString.length()){
            if(Character.isDigit (regString.charAt(i))){
                number = number + regString.charAt(i);
            }
            i++;
        }
        if(number.length() != 10 || number.length() == 0){
            return("This WackyString is not a phone number.");
        }
        phone = "(" + number.substring(0,3) + ") " + number.substring(3,6) + "-" + number.substring(6,10);
        return phone;
    }

    public void setWackyString(String string){
        if(string == null){
            throw new IllegalArgumentException("String cannot be null");
        } else {
            regString = string;
        }
    }

    public String getWackyString(){
        return regString;
    }

    public void ramifyString(){
        String begin;
        String end;
        String vcu = "Go Rams";
        String rams = "CS@VCU";
        outerloop:
        for(int i = 0;i < regString.length();i++)
        {
            if (regString.charAt(i) == '0' && ((i+1 < regString.length()) && (regString.charAt(i+1) =='0')) &&
                    (i+2 < regString.length() && regString.charAt(i+2) == '0'))
            {
                if(i+3 > regString.length())
                {
                    i = regString.length();
                    break;
                }
                i+=3;
                while(i < regString.length() && regString.charAt(i) == '0')
                {
                    i++;
                    if(i == regString.length())
                    {
                        break outerloop;
                    }
                }
            }

            if (regString.charAt(i) =='0' && (((i+1 < regString.length()) && (regString.charAt(i+1) !='0')) || i+1 >= regString.length()))
            {
                begin = regString.substring(0,i);
                end = regString.substring(i+1, regString.length());
                regString = begin + vcu + end;
            }

            if(regString.charAt(i) == '0' && ((i+1 < regString.length()) && (regString.charAt(i+1) =='0')))
            {
                begin = regString.substring(0,i);
                end = regString.substring(i+2, regString.length());
                regString = begin + rams + end;
            }
        }
    }

    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IndexOutOfBoundsException, IllegalArgumentException {
        if(startPosition > regString.length() || startPosition < 1 || endPosition < 1 || endPosition > regString.length()){
            throw new IndexOutOfBoundsException();
        }
        if(startPosition > endPosition){
            throw new IllegalArgumentException();
        }
        startPosition -= 1;
        endPosition -= 1;
        String temp = "";
        for(int i = 0; i < regString.length(); i++){
            char ch = regString.charAt(i);
            if(i < startPosition || i > endPosition || (ch <= '0' || ch > '9')){
                temp += ch;
            } else {
                temp += getRoman(ch - '0');
            }
        }
        regString = temp;
    }

    private String getRoman(int num) {
        if (num == 1) {
            return "I";
        } else if (num == 2) {
            return "II";
        } else if (num == 3) {
            return "III";
        } else if (num == 4) {
            return "IV";
        } else if (num == 5) {
            return "V";
        } else if (num == 6) {
            return "VI";
        } else if (num == 7) {
            return "VII";
        } else if (num == 8) {
            return "VIII";
        } else if (num == 9) {
            return "IX";
        } else {
            return "0";
        }
    }
}

