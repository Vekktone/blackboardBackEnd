package com.blackboard.backEnd.service;

import com.blackboard.backEnd.dao.StudentDAO;
import com.blackboard.backEnd.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class FileParser {

    @Autowired
    StudentDAO studentDAO;

    /**
     * parses the file and looks for each field, creates customer obj, adds to final list to return.
     * @param filename name of file to parse
     * @return list of customers to add
     * @throws FileNotFoundException if the file is not found
     */
    List<Student> parseFile(String filename) throws FileNotFoundException {

        File file = new File("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/file-uploads/" + filename);
        List<Student> cList = new ArrayList<>();
        Scanner input = new Scanner(file);

        {
            try {
                input = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        while (input.hasNext()) {
            String nextToken = input.nextLine();
            String[] split = nextToken.split(",");
            for (int i = 0; i < split.length; i++){
                split[i] = split[i].replaceAll("^\"|\"$", "");
            }
            System.out.println(Arrays.toString(split));
            //check for duplicates, then add
            Student c = new Student();
            c.setFirstName(split[0]);
            c.setLastName(split[1]);
            c.setEmail(split[2]);
            c.setAddress(split[3]);
            c.setCity(split[4]);
            c.setState(split[5]);
            c.setZip(split[6]);
            cList.add(c);

        }

        input.close();
        return cList;
    }

}
