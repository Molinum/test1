import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Expendedor exp = new Expendedor(5,400);
        Moneda m = null;
        Comprador c=null;
        m = new Moneda500();
        c = new Comprador(m,654,exp);
        System.out.println(c.queBebiste()+", "+c.cuantoVuelto());
    }
}
abstract class Moneda {
    public Moneda(){};
    public Moneda getSerie(){
        return this;
    };
    public abstract int getValor();
}

class Moneda1500 extends Moneda{
    public Moneda1500(){}

    @Override
    public int getValor(){
        return 1500;
    }
}
class Moneda1000 extends Moneda{
    public Moneda1000(){}
    @Override
    public int getValor() {
        return 1000;
    }
}
class Moneda500 extends Moneda{
    public Moneda500(){}
    @Override
    public int getValor() {
        return 500;
    }
}
class Moneda100 extends Moneda{
    public Moneda100(){}
    @Override
    public int getValor() {
        return 100;
    }
}

class Expendedor{
    public static final int  COCA=1;
    public static final int  SPRITE=2;
    public int precio;
    private Deposito coca = new Deposito();
    private Deposito sprite = new Deposito();
    private Deposito monedasVuelto = new Deposito();
    public Expendedor(int numBebidas , int precioBebidas){
        this.precio = precioBebidas;
        for (int i= 0; i<numBebidas; i++){
            CocaCola coc = new CocaCola(1);
            coca.agregarElemento(coc);
            Sprite spr = new Sprite(1);
            sprite.agregarElemento(spr);
        }
    }
    public Bebida comprarBebida(Moneda m, int cual) {
        if (m != null) {
            if (cual == COCA) {
                if (m.getValor() >= precio) {
                    CocaCola temp = (CocaCola) coca.removerElemento();
                    if (temp != null) {
                        for (int i = 0; i < (m.getValor() - precio) / 100; i++) {
                            Moneda100 mon = new Moneda100();
                            monedasVuelto.agregarElemento(mon);
                        }
                    } else {
                        for (int i = 0; i < m.getValor() / 100; i++) {
                            Moneda100 mon = new Moneda100();
                            monedasVuelto.agregarElemento(mon);
                        }

                    }
                    return temp;
                } else {
                    for (int i = 0; i < m.getValor() / 100; i++) {
                        Moneda100 mon = new Moneda100();
                        monedasVuelto.agregarElemento(mon);
                    }
                    return null;
                }
            } else if (cual == SPRITE) {

                if (m.getValor() >= precio) {
                    Sprite temp = (Sprite) sprite.removerElemento();
                    if (temp != null) {
                        for (int i = 0; i < (m.getValor() - precio) / 100; i++) {
                            Moneda100 mon = new Moneda100();
                            monedasVuelto.agregarElemento(mon);
                        }
                    } else {
                        for (int i = 0; i < m.getValor() / 100; i++) {
                            Moneda100 mon = new Moneda100();
                            monedasVuelto.agregarElemento(mon);
                        }
                    }
                    return temp;
                } else {
                    for (int i = 0; i < m.getValor() / 100; i++) {
                        Moneda100 mon = new Moneda100();
                        monedasVuelto.agregarElemento(mon);
                    }
                    return null;
                }
            }else{
                for (int i = 0; i < m.getValor() / 100; i++) {
                    Moneda100 mon = new Moneda100();
                    monedasVuelto.agregarElemento(mon);
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public Moneda getVuelto(){
        return (Moneda100) monedasVuelto.removerElemento();
    }

}

abstract class Bebida{
    private int serie;
    public Bebida(int numSerie){
        this.serie = numSerie;
    };
    public int getSerie(){
        return serie;
    };
    public abstract String beber();
}
class CocaCola extends Bebida{

    public CocaCola(int numSerie) {
        super(numSerie);
    }
    @Override
    public String beber() {
        return "cocacola";
    }
}
class Sprite extends Bebida{

    public Sprite(int numSerie) {
        super(numSerie);
    }

    @Override
    public String beber() {
        return "sprite";
    }
}

class Deposito {
    private ArrayList<Object> elementos;

    public Deposito() {
        elementos = new ArrayList<>();

    }

    public void agregarElemento(Object elemento) {
        elementos.add(elemento);
    }

    public Object removerElemento() {
        if(elementos.size()>0) {
            return elementos.remove(elementos.size() - 1);
        }
    else return null;
    }
}
class Comprador {
    private String sonido;
    private int vuelto = 0;

    public Comprador(Moneda m, int queBebida, Expendedor exp) {
        if (queBebida == Expendedor.COCA) {
            Bebida com = exp.comprarBebida(m, 1);
            if (com != null) {
                this.sonido = "cocacola";
            }
            while (true) {
                Moneda c = exp.getVuelto();
                if (c == null) break;
                vuelto += c.getValor();
            }
        } else if (queBebida == Expendedor.SPRITE) {
            Bebida com = exp.comprarBebida(m, 2);
            if (com != null) {
                this.sonido = "sprite";
            }
            while (true) {
                Moneda c = exp.getVuelto();
                if (c == null) break;
                vuelto += c.getValor();
            }
        } else {
            this.vuelto = m.getValor();
        }
    }
    public String queBebiste() {
        return sonido;
    }
    public int cuantoVuelto() {
        return vuelto;
    }
}

