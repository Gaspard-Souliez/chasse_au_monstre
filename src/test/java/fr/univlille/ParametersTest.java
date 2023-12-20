package test.java.fr.univlille;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;

public class ParametersTest {

    private Parameters para = new Parameters();

    @Test
    void testParameters() {
        assertEquals(Parameters.labyrinthKnowledge, AllParameters.COMPLETE_KNOWLEDGE);
        //assertEquals(Parameters.movement, AllParameters.MOVEMENT_8);
        assertEquals(Parameters.mouvementPartialKnowledgeValue, 2);
        assertEquals(Parameters.lengthLabyrinth[1], 20);
        assertEquals(Parameters.lengthLabyrinth[0], 30);
        
    }

    @Test
    void testUpdateParameters() {
        para.setRowMaze(20);
        para.setColMaze(30);
        para.updateParameters();
        assertEquals(Parameters.lengthLabyrinth[0], 30);
        assertEquals(Parameters.lengthLabyrinth[1], 20);
    }
}