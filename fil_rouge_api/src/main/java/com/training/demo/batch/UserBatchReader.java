package com.training.demo.batch;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.training.demo.batch.dto.ImportUserDto;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

@Component
public class UserBatchReader implements ItemReader<ImportUserDto>, StepExecutionListener {

    private CSVReader csvReader;
    private FileReader fileReader;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobParameters();
        String csvFileName = jobParameters.getString("fileName");

        try {
            // Récupération du chemin du fichier CSV dans le context du projet
            ClassLoader classLoader = this.getClass().getClassLoader();
            if (fileReader == null) {
                URL url = classLoader.getResource(csvFileName);
                if (url == null)
                    throw new FileNotFoundException("fichier url null");
                String loadedFilename = classLoader.getResource(csvFileName).getFile();
                // Initialise le lecteur du fichier
                fileReader = new FileReader(loadedFilename);
            }
            // Initialise le lecteur du CSV (basé sur openCSV)
            csvReader = new CSVReader(fileReader);
        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable");
        }
    }

    @Override
    public ImportUserDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // reading one line from CSV File
        try {
            // Lecture d'une ligne
            String[] currentLine = csvReader.readNext();

            if (currentLine != null) {
                ImportUserDto dto = ImportUserDto
                        .builder()
                        .firstname(currentLine[0])
                        .lastname(currentLine[1])
                        .mail(currentLine[2])
                        .pass(currentLine[3])
                        .build();
                return dto;
            }
        } catch (CsvValidationException e) {
            System.err.println("Ligne CSV Invalide");
        } catch (IOException e) {
            System.err.println("I/O Exception");
        }
        // Si la ligne est vide, on retourne null
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Reader COMPLETED");
        // Fermeture du lecteur de CSV pour éviter les fuite mémoires
        try {
            if (csvReader != null) {
                csvReader.close();
            }
        } catch (IOException e) {
            System.err.println("Fermeture CSV Reader impossible");
        }
        return ExitStatus.COMPLETED;
    }
}
