package template;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.colorchooser.ColorSelectionModel;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Modelo de projeto básico da JSGE.
 * 
 * JSGE basic project template.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {

    private int[] array;
    Vector2 retanguloSelection;
    private List<int[]> listaSelection;
    private int posSelection;

    private int[] array2;
    Vector2 retanguloInsertion;
    private List<int[]> listaInsertion;
    private int posInsertion;

    private int[] array3;
    Vector2 retanguloBubble;
    private List<int[]> listaBubble;
    private int posBubble;

    private int[] array4;
    Vector2 retanguloMerge;
    private List<int[]> listaMerge;
    private int posMerge;

    private double tempoParaMudar;
    private double contadorTempo;

    public Main() {

        super(
                800, // largura / width
                450, // algura / height
                "Window Title", // título / title
                60, // quadros por segundo desejado / target FPS
                true, // suavização / antialiasing
                false, // redimensionável / resizable
                false, // tela cheia / full screen
                false, // sem decoração / undecorated
                false // sempre no topo / always on top
        );

    }

    /**
     * Cria o mundo do jogo.
     * Esse método executa apenas uma vez durante a inicialização da engine.
     * 
     * Creates the game world.
     * This method runs just one time during engine initialization.
     */
    @Override
    public void create() {

        array  = gerarArray(10);
        array2 = gerarArray(10);
        array3 = gerarArray(10);
        array4 = gerarArray(10);

        //new int[] { 9, 3, 1, 5, 4, 8, 10, 9, 2 }
        retanguloSelection = new Vector2(50, 150);
        retanguloInsertion = new Vector2(getScreenWidth() / 2 + 50, 150);
        retanguloBubble = new Vector2(50, getScreenHeight() / 2 + 180);
        retanguloMerge = new Vector2(450, getScreenHeight() / 2 + 180);

        listaSelection = new ArrayList<>();
        listaInsertion = new ArrayList<>();
        listaBubble = new ArrayList<>();
        listaMerge = new ArrayList<>();

        tempoParaMudar = 1;
        copiarArray(listaSelection, array);
        ordenarSelection(array);

        copiarArray(listaInsertion, array2);
        ordenarInsertion(array2);

        copiarArray(listaBubble, array3);
        ordenarBubble(array3);

        copiarArray(listaMerge, array4);
        ordenarMerge(array4);

    }

    /**
     * Lê a entrada do usuário e atualiza o mundo do jogo.
     * Os métodos de entrada devem ser usados aqui.
     * Atenção: Você NÃO DEVE usar nenhum dos métodos de desenho da engine aqui.
     * 
     * 
     * Reads user input and update game world.
     * Input methods should be used here.
     * Warning: You MUST NOT use any of the engine drawing methods here.
     * 
     * @param delta O tempo passado, em segundos, de um quadro para o outro.
     *              Time passed, in seconds, between frames.
     */
    @Override
    public void update(double delta) {

        contadorTempo += delta;

        if (contadorTempo > tempoParaMudar) {
            
            contadorTempo = 0;
            if (posSelection < listaSelection.size() - 1) {
                posSelection++;
            }

            if (posInsertion < listaInsertion.size() - 1) {
                posInsertion++;
            }


            if (posBubble < listaBubble.size() - 1) {       
                posBubble++;
            }

            if(posMerge < listaMerge.size() - 1){
                posMerge++;
            }
        }
    }

    /**
     * Desenha o mundo do jogo.
     * Todas as operações de desenho DEVEM ser feitas aqui.
     * 
     * Draws the game world.
     * All drawing related operations MUST be performed here.
     */
    @Override
    public void draw() {

        clearBackground(WHITE);

        desenharArraySelection(listaSelection.get(posSelection), retanguloSelection, 10, 30, 10);
        desenharArrayInsertion(listaInsertion.get(posInsertion), retanguloInsertion, 10, 30, 10);
        desenharArrayBubble(listaBubble.get(posBubble), retanguloBubble, 10, 30, 10);
        desenharArrayMerge(listaMerge.get(posMerge), retanguloMerge, 10, 30, 10);
        drawLine(getScreenWidth()/2, 0, getScreenWidth()/2, getScreenHeight(), BEIGE);
        //drawFPS(20, 20);
        drawLine(0, getScreenHeight()/2, getScreenWidth(), getScreenHeight()/2, BEIGE);

        drawText("Selection Sort", 60, getScreenHeight()/2 - 25, 15, BLACK);
        drawText("Insertion Sort", getScreenWidth()/2 + 60, getScreenHeight()/2 - 25, 15, BLACK);
        drawText("Bubble Sort", 60, getScreenHeight()/2 + 200, 15, BLACK);
        drawText("Merge Sort", getScreenWidth()/2 + 60, getScreenHeight()/2 + 200, 15, BLACK);
    }

    public void desenharArraySelection(int array[], Vector2 retangulo, int largura, int espacamento,
            int tamanhoPedaco) {

        for (int i = 0; i < array.length; i++) {
            drawText(array[i] + "", retangulo.x + i * espacamento, (retangulo.y - array[i] * tamanhoPedaco) - 20, DARKGREEN);
            fillRectangle(
                    retangulo.x + espacamento * i,
                    retangulo.y - array[i] * tamanhoPedaco,
                    largura,
                    array[i] * tamanhoPedaco,
                    PINK);
        }
    }

    public void desenharArrayInsertion(int array[], Vector2 retangulo, int largura, int espacamento,
            int tamanhoPedaco) {

        for (int i = 0; i < array.length; i++) {
            drawText(array[i] + "", retangulo.x + i * espacamento, (retangulo.y - array[i] * tamanhoPedaco) - 20, DARKGREEN);
            fillRectangle(
                    retangulo.x + espacamento * i,
                    retangulo.y - array[i] * tamanhoPedaco,
                    largura,
                    array[i] * tamanhoPedaco,
                    PINK);
        }
    }

    public void desenharArrayBubble(int array[], Vector2 retangulo, int largura, int espacamento, int tamanhoPedaco) {

        for (int i = 0; i < array.length; i++) {
            drawText(array[i] + "", retangulo.x + i * espacamento, (retangulo.y - array[i] * tamanhoPedaco) - 20, DARKGREEN);
            fillRectangle(
                    retangulo.x + espacamento * i,
                    retangulo.y - array[i] * tamanhoPedaco,
                    largura,
                    array[i] * tamanhoPedaco,
                    PINK);
        }
    }

    public void desenharArrayMerge(int array[], Vector2 retangulo, int largura, int espacamento, int tamanhoPedaco) {

        for (int i = 0; i < array.length; i++) {
            drawText(array[i] + "", retangulo.x + i * espacamento, (retangulo.y - array[i] * tamanhoPedaco) - 20, DARKGREEN);
            fillRectangle(
                    retangulo.x + espacamento * i,
                    retangulo.y - array[i] * tamanhoPedaco,
                    largura,
                    array[i] * tamanhoPedaco,
                    PINK);
        }
    }

    private void copiarArray(List<int[]> lista, int[] array) {
        int[] novoArray = new int[array.length];
        System.arraycopy(array, 0, novoArray, 0, array.length);
        lista.add(novoArray);
    }

    private void ordenarSelection(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }
            int t = array[i];
            array[i] = array[menor];
            array[menor] = t;
            copiarArray(listaSelection, array);
        }

    }

    private void ordenarInsertion(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            int chave = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j = j - 1;

            }

            array[j + 1] = chave;
            copiarArray(listaInsertion, array);
        }
    }

    private void ordenarBubble(int[] array) {
        int auxiliar = 0;
        boolean trocou;
        for(int i = 0; i<array.length - 1; i++){
            trocou = false;
            for(int j = 0; j<array.length - i - 1; j++){
                
                if(array[j] > array[j + 1]){
                    trocou = true;
                    auxiliar = array[j];
                    array[j] = array[j+1];
                    array[j+1] = auxiliar;
                    
                }
                copiarArray(listaBubble, array);
            }

            if(!trocou){
                break;
            }
            
        }
    }

    private void ordenarMerge(int[] array) {
        
        for (int tamanhoAtual = 1; tamanhoAtual <= array.length - 1; 
            tamanhoAtual = 2 * tamanhoAtual) {
            

            for (int iniEsq = 0; iniEsq < array.length - 1; 
            iniEsq += 2 * tamanhoAtual) {
                

                int meio = Math.min(iniEsq + tamanhoAtual - 1, array.length - 1);
                int finalDireita = Math.min(iniEsq + 2 * tamanhoAtual - 1, array.length - 1);
                

                merge(array, iniEsq, meio, finalDireita);
                copiarArray(listaMerge, array);
            }
        }
    }

    static void merge(int[] array, int esquerda, int meio, int direita) {
        
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;
        

        int[] arr1 = new int[n1], arr2 = new int[n2];
        
        for (int i = 0; i < n1; i++)
            arr1[i] = array[esquerda + i];
        for (int j = 0; j < n2; j++)
            arr2[j] = array[meio + 1 + j];
        
        int i = 0;    
        int j = 0;    
        int k = esquerda; 
        

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                array[k] = arr1[i];
                i++;
            } else {
                array[k] = arr2[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            array[k] = arr1[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = arr2[j];
            j++;
            k++;
        }
    }

    public int[] gerarArray(int tamanho){

        Random random = new Random();
       
        int[] array = new int[tamanho];

        for(int i = 0; i < array.length; i++){
            int x = random.nextInt(13) + 1;
            array[i] = x;
        }

        return array;

    }

    /**
     * Instancia a engine e a inicia.
     * 
     * Instantiates the engine and starts it.
     */
    public static void main(String[] args) {
        new Main();
    }

}
