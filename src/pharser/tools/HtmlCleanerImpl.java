package pharser.tools;

public class HtmlCleanerImpl implements HtmlCleaner{
    @Override
    public String clean(String text) {
        StringBuilder result = new StringBuilder();
        char[] temp = text.toCharArray();
        int index = -2;
        int mark = 0;
        int lastIndex = 0;

        while (index != -1){
            index = text.indexOf("<",index+1);
            if (index == -1 ){
                result.append(" ");
                for (int i=lastIndex+1; i<text.length(); i++){
                    result.append(temp[i]);
                }
            } else {

                String substring = text.substring(index+1,index+6);
                if (substring.equalsIgnoreCase("scrip")){
                    index = text.indexOf("</script",index);
                    mark = index;
                }
                if (substring.equalsIgnoreCase("style")){
                    index = text.indexOf("</style",index);
                    mark = index;
                }
                if (substring.equalsIgnoreCase("objec")){
                    index = text.indexOf("</objec",index);
                    mark = index;
                }

                result.append(" ");
                for (int i=mark; i<index; i++){
                    result.append(temp[i]);
                }
                mark = index;
                index = text.indexOf("<",mark+1);
                lastIndex = text.indexOf(">",mark+1);
                if (index == -1) {
                    result.append(" ");
                    for (int i=lastIndex+1; i<text.length(); i++){
                        result.append(temp[i]);
                    }
                    break;
                }
                if (index<lastIndex){
                    result.append(" ");
                    for (int i=mark; i<index; i++){
                        result.append(temp[i]);
                    }
                    result.append(" ");
                    mark = index;
                } else {
                    mark = lastIndex+1;
                    index = lastIndex;
                }
            }
        }
        return result.toString();
    }

    private int removeSpecialTags(int index, String text) {
        try{
            String temp = text.substring(index+1,index+6);
            if (temp.equalsIgnoreCase("scrip")){
                return text.indexOf("</script",index);
            }
            if (temp.equalsIgnoreCase("style")){
                return text.indexOf("</style",index);
            }
            if (temp.equalsIgnoreCase("objec")){
                return text.indexOf("</objec",index);
            }
        } catch (ArrayIndexOutOfBoundsException ex){
            //NOP
        }
        return index;
    }
}
