package corsi.prog2.ministerio.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resumen {

    private Double recaudacion;
//    private Preparacion preparacionMasPedida;
    private Investigador vigilanteMasPedidos;

    public Double getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Double recaudacion) {
        this.recaudacion = recaudacion;
    }

//    public Preparacion getPreparacionMasPedida() {
//        return preparacionMasPedida;
//    }
//
//    public void setPreparacionMasPedida(Preparacion preparacionMasPedida) {
//        this.preparacionMasPedida = preparacionMasPedida;
//    }





    public Investigador getVigilanteMasPedidos() {
        return vigilanteMasPedidos;
    }

    public void setVigilanteMasPedidos(Investigador vigilanteMasPedidos) {
        this.vigilanteMasPedidos = vigilanteMasPedidos;
    }
    
//    public void finalizarJornada(List<Comanda> comandas) {
//        double recaudacion = 0;
//        Map<Usuario, Integer> atencion = new HashMap<>();
//        Map<Item, Integer> bebidas = new HashMap<>();
//        Map<Item, Integer> preparaciones = new HashMap<>();
//
//        for (Comanda c : comandas) {
//            if (atencion.containsKey(c.getVigilante())) {
//                atencion.replace(c.getVigilante(), atencion.get(c.getVigilante()) + 1);
//            } else {
//                atencion.put(c.getVigilante(), 1);
//            }
//            List<Item> items = c.getItems();
//            for (Item i : items) {
//                Map<Item, Integer> bp = i.getTipo().equals("Bebida") ? bebidas : preparaciones;
//                if (bp.containsKey(i)) {
//                    bp.replace(i, bp.get(i) + 1);
//                } else {
//                    bp.put(i, 1);
//                }
//                recaudacion += i.getPrecio();
//            }
//        }
//
//        setVigilanteMasPedidos((Investigador) obtenerMaximo(atencion));
//        setPreparacionMasPedida((Preparacion) obtenerMaximo(preparaciones));
//        setRecaudacion(recaudacion);
//    }

    private <K, V extends Comparable<V>> K obtenerMaximo(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (maxEntry == null
                    || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }
}
