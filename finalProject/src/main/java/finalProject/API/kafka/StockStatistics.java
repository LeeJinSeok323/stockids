package finalProject.API.kafka;

public class StockStatistics {
    public static String get(String oldValue, String newValue) {
        // 초기값 처리
        if(oldValue.isEmpty()){
            return newValue+":max=0:min=-1:start="+getValue(newValue);
        }
        int curValue = getValue(newValue);
        int max = 0; int min = -1; int start=0;

        String[] data = oldValue.split(":");
        for (String d : data) {
            // "key=value" 형태인지 체크
            String[] keyValue = d.split("=", 2);

            String key = keyValue[0].trim();
            String valueStr = keyValue[1].trim();

            // 숫자 변환 시 예외 발생 가능성도 고려
            int value;
            try {
                value = Integer.parseInt(valueStr);
            } catch (NumberFormatException e) {
                continue;
            }
            switch (key) {
                case "max":
                    max = value;
                    if (curValue > max) {
                        max = curValue;
                    }
                    break;
                case "min":
                    min = value;
                    if (min == -1 || curValue < min) {
                        min = curValue;
                    }
                    break;
                case "start":
                    start = value;
                    if(start == 0){
                        start = curValue;
                    }
                    break;
                default:
                    // 필요한 경우, 처리하지 않는 키는 그냥 무시
                    break;
            }
        }

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
