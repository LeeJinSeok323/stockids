package finalProject.API.kafka;

public class StockStatistics {
    public static String get(String oldValue, String newValue) {
        // 초기값 처리
        if(oldValue.isEmpty()){
            return newValue+":max=0:min=-1:start="+getValue(newValue);
        }
        int curValue = getValue(newValue);
        int max = 0; int min = 0; int start=0;

        String[] data = oldValue.split(":");
        for(String d : data){
            String[] temp = d.split("=");
            if(temp[0].trim().equals("max"))
                max = Integer.parseInt(temp[1].trim());
            else if(temp[0].trim().equals("min"))
                min = Integer.parseInt(temp[1].trim());
            else if(temp[0].trim().equals("start"))
                start = Integer.parseInt(temp[1].trim());
            else
                continue;
        }
        if(curValue > max)
            max = curValue;
        if(min == -1 || curValue < min)
            min = curValue;
        return newValue+":max="+max+":min="+min+":start="+start;
    }

    public static int getValue(String data){
        try{
            String[] dataArray = data.split(":");
            int value = -1;
            String[] temp;
            for(String d : dataArray){
                temp = d.split("=");
                if(temp[0].trim().equals("dealPrice")){
                    value = Integer.parseInt(temp[1].trim());
                    break;
                }
            }
            return value;
        }catch(Exception e){
            return -2;
        }

    }

}
