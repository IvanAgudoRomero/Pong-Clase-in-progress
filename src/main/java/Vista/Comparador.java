package Vista;

import java.util.Comparator;

public class Comparador implements Comparator<String> {
//registro = ganador+"#"+perdedor+"#"+difGoles+"#"+fecha;
    @Override
    public int compare(String o1, String o2) {
        String[] datos1 = o1.split("#");
        String[] datos2 = o1.split("#");
        if(Integer.parseInt(datos1[2])>Integer.parseInt(datos2[2])){
            return 1;
        }else if(Integer.parseInt(datos1[2])<Integer.parseInt(datos1[2])){
            return -1;
        }else{
            return 0;
        }
        //si son iguales 0 y si es mayor 1 y si es menor un -1
    }
}
