package ua.training.util;



import org.junit.Before;
import org.junit.Test;
import ua.training.model.Wagon;
import ua.training.model.WagonComfortType;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class FileUtilTest  {



   private FileUtil fileUtil = new FileUtil();
   private String FILE_PATH = "C:\\wagon.csv";
   private ArrayList<Wagon> wagon;

    @Before
    public void setup()
            throws IOException
    {
        wagon =(ArrayList<Wagon>)fileUtil.readFile(FILE_PATH);

    }

     @Test
    public void FileTestNotNull() throws IOException {
        int id = wagon.get(1).getId();
        WagonComfortType type = wagon.get(1).getType();
        int passengers = wagon.get(1).getNumberOfPassengers();
        int luggage = wagon.get(1).getAmountOfLuggage();
        assertNotNull(id );
        assertNotNull(passengers );
        assertNotNull(type);
        assertNotNull(luggage);
    }

/*    @Test(expected = FileNotFoundException.class)
    public void testReadFile() throws IOException {
    fileUtil.readFile(FILE_PATH);
    }

    @Test(expected = FileNotFoundException.class)
    public void testWriteFile() throws IOException {
     fileUtil.writeFile(FILE_PATH,wagon);
    }*/



}