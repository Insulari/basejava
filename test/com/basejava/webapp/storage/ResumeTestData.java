package com.basejava.webapp.storage;

import com.basejava.webapp.model.*;

import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {

        Resume resume = new Resume("Григорий Кислин");
        Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class);
        contacts.put(ContactType.PHONE, "+7 912 345 6789");
        contacts.put(ContactType.SKYPE, "Kislin");

        resume.addContacts(contacts);

        Map<SectionType, Section> sections = new EnumMap<SectionType, Section>(SectionType.class);
        sections.put(SectionType.OBJECTIVE, new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        resume.addSections(sections);

        System.out.println(resume.toString());
    }
}
