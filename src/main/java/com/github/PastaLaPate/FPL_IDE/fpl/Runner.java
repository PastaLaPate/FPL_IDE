package com.github.PastaLaPate.FPL_IDE.fpl;

import com.github.PastaLaPate.FPL_IDE.ui.panels.Runner.Result;
import com.github.PastaLaPate.FPL_IDE.util.downloader.Downloader;
import com.github.PastaLaPate.FPL_IDE.util.logger.Level;
import com.github.PastaLaPate.FPL_IDE.util.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Runner {
    public Runner() {

    }

    public void Run() throws IOException {
        Logger.log("launching main.fpl", this.getClass(), Level.INFO);
        String program_File;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            program_File = "French_Programming_Language.exe";
        } else {
            program_File = "French_Programming_Language.out";
        }
        String command = Downloader.getPathFolder() + program_File + Downloader.getPathFolder() + "main.fpl" ;
        Logger.log("launching with command : " + command, this.getClass(), Level.INFO);
        Result result = new Result();
        result.init();
        try {
            // Use a ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(Downloader.getPathFolder() + program_File, Downloader.getPathFolder() + "main.fpl");

            Process p = pb.start();
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                result.addLine(line);
                Logger.log("FPL : " + line, this.getClass(), Level.INFO);
            }
            p.waitFor(); // Let the process finish.
            result.addLine("Finished");
        } catch (Exception e) {
            Logger.log(e);
        }
    }
}
