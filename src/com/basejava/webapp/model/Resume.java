package com.basejava.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mustn't be null");
        Objects.requireNonNull(fullName, "fullName mustn't be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    public void addContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public void addSections(Map<SectionType, Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder strResume = new StringBuilder("| ID: " + uuid + " | Name: " + fullName + " |\n\n");
        final String LINE =  "__________________________________________________\n";
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            strResume.append(entry.getKey().getTitle())
                     .append(entry.getValue())
                     .append('\n');
        }
        strResume.append(LINE);
        for (Map.Entry<SectionType, Section> entry : sections.entrySet()
             ) {
            strResume.append(entry.getKey().getTitle())
                     .append(":\n")
                     .append(entry.getValue())
                     .append('\n');
        }
        strResume.append(LINE);
        return strResume.toString();
    }

    @Override
    public int compareTo(Resume r) {
        int c = fullName.compareTo(r.getName());
        return (c != 0) ? c : uuid.compareTo(r.getUuid());
    }
}