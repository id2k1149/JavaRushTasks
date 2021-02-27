package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery {
    private List<Record> records;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private class Record {
        String ip;
        String user;
        Date date;
        Event event;
        Integer taskNumber;
        Status status;

        @Override
        public String toString() {
            return "Record{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", taskNumber=" + taskNumber +
                    ", status=" + status +
                    '}';
        }
    }

    public LogParser(Path logDir) {
        records = new ArrayList<>();

        readRecords(logDir);
    }

    private void readRecords(Path logDir) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path log : directoryStream) {
                if (Files.isRegularFile(log) && log.toString().endsWith(".log")) {
                    BufferedReader reader = Files.newBufferedReader(log, StandardCharsets.UTF_8);
                    while (reader.ready()) {
                        Record record = new Record();
                        String[] entry = reader.readLine().split("\t");
                        record.ip = entry[0];
                        record.user = entry[1];
                        record.date = formatter.parse(entry[2]);

                        if (entry[3].indexOf(' ') == -1) {
                            record.event = Event.valueOf(entry[3]);
                            record.taskNumber = null;
                        } else {
                            String[] event = entry[3].split(" ");
                            record.event = Event.valueOf(event[0]);
                            record.taskNumber = Integer.parseInt(event[1]);
                        }

                        record.status = Status.valueOf(entry[4]);

                        records.add(record);
                    }
                    reader.close();
                } else {
                    if (Files.isDirectory(log)) {
                        readRecords(log);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Set<Record> getFilteredEntries(Date after, Date before) {

        Set<Record> filteredRecords = new HashSet<>();
        for (Record record : records) {
            if (isBetween(record.date, after, before)) {
                filteredRecords.add(record);
            }
        }

        return filteredRecords;
    }

    private boolean isBetween(Date date, Date after, Date before) {
        return (after == null || date.after(after) || date.equals(after)) &&
                (before == null || date.before(before) || date.equals(before));
    }

    @Override
    // должен возвращать количество уникальных IP адресов за выбранный период
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> ips = getUniqueIPs(after, before);
        return ips.size();
    }

    @Override
    // должен возвращать множество, содержащее все не повторяющиеся IP
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<Record> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Record record : filteredRecords) {
            ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, с которых работал переданный пользователь.
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<Record> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Record record : filteredRecords) {
            if (record.user.equals(user))
                ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, с которых было произведено переданное событие.
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<Record> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Record record : filteredRecords) {
            if (record.event.equals(event))
                ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, события с которых закончилось переданным статусом.
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<Record> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Record record : filteredRecords) {
            if (record.status.equals(status))
                ips.add(record.ip);
        }

        return ips;
    }

/*
    UserQuery implementation
*/

    @Override
    // Метод должен возвращать множество содержащее всех пользователей.
    public Set<String> getAllUsers() {
        Set<String> allUsers = new HashSet<>();
        return null;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return 0;
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return 0;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return null;
    }
}