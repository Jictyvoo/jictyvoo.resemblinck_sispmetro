package br.uefs.ecomp.controller;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import br.uefs.ecomp.util.Graph;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
	
	private Graph testGraph;
	
	@Before
	public void setUp() {
		FileWriter arq;
		PrintWriter gravarArq = null;
		try {
			arq = new FileWriter("../initialize/estacoesMetro.spsubsta");
			gravarArq = new PrintWriter(arq);
			gravarArq.print("# Arquivo descrevendo o tempo entre as esta��es do Metr� de S�o Paulo.\r\n" + 
				"# Linhas iniciadas com o caractere '#' representam coment�rios e devem ser ignoradas.\r\n" + 
				"# Cada linha do arquivo est� no seguinte formato:\r\n" + 
				"# <Esta��o A>, <Esta��o B>, <tempo>\r\n" + 
				"# A <Esta��o A> representa a esta��o de origem, a <Esta��o B> representa a\r\n" + 
				"# esta��o de destino, e <tempo> � o tempo que um trem leva para ir de A a B.\r\n" + 
				"# Uma conex�o entre uma esta��o A e B implica necessariamente que haja\r\n" + 
				"# uma conex�o entre B e A, e o tempo que o trem leva para ir de A a B � igual ao\r\n" + 
				"# tempo que o trem leva para ir de B para A.\r\n" + 
				"#\r\n" + 
				"#Linha 1\r\n" + 
				"#\r\n" + 
				"Tucuruvi, Parada Inglesa, 2\r\n" + 
				"Parada Inglesa, Jardim S�o Paulo-Ayrton Senna, 2\r\n" + 
				"Jardim S�o Paulo-Ayrton Senna, Santana, 3\r\n" + 
				"Santana, Carandiru, 2\r\n" + 
				"Carandiru, Portuguesa-Tiet�, 2");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			gravarArq.close();
		}
		this.testGraph = new Graph();
	}
	
	@Test
	public void testReadFile() {
		try {
			Controller.getInstance().parseFile("../initialize/estacoesMetro.spsubsta", this.testGraph);
		} catch (FileNotFoundException e) {
			assertFalse(true);
		}
		assertEquals(6, this.testGraph.getNumOfVertex());
		assertEquals(5, this.testGraph.getNumOfEdges());
	}
}
