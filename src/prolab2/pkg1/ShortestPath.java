/*******************************************************************************
Kaynak siteler: 
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/

https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/

*******************************************************************************/

package prolab2.pkg1;

import java.lang.*;

class ShortestPath {

    static final int V = 82;
    public int distance[] = new int[V];

    public int[] getDist() {
        return distance;
    }

    int minDistance(int dist[], Boolean sptSet[]) {
        // Min değeri başlat.
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    void dijkstra(int graph[][], int src) {
        // Çıktı dizisi. dist[i] src ile i arasındaki en kısa mesafeyi tutar.

        /* Köşe i, en kısa yol ağacına veya src ile i arasındaki en kısa mesafe 
        dahil edilmişse sptSet [i] geçerli olacaktır */
        Boolean sptSet[] = new Boolean[V];

        // Tüm mesafeleri INFINITE ve stpSet[] olarak false olarak başlat.
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Başlangıç noktasının kendisinden uzaklığı her zaman 0'dır 
        distance[src] = 0;

        // Tüm köşeler için en kısa yolu bulun.
        for (int count = 0; count < V - 1; count++) {
            /* Henüz işlenmemiş köşe kümesinden minimum mesafe tepe noktasını seçin. 
            U her zaman ilk yinelemede src'ye eşittir.*/
            int u = minDistance(distance, sptSet);

            // Seçilmiş tepe noktasını ziyaret edilmiş olarak işaretleme.
            sptSet[u] = true;

            // Toplanan tepe noktasının bitişik köşelerinin dağıtım değerini güncelleyin. 
            for (int v = 0; v < V; v++) /*Dist [v] 'yi yalnızca sptSet içinde değilse, 
             u'dan v'ye bir kenar vardır ve src'den v'den u'ya olan yolun toplam ağırlığı, 
             dist [v]' nin geçerli değerinden daha küçüktür */ {
                if (!sptSet[v] && graph[u][v] != 0
                        && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
    }
}
