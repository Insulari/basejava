package com.basejava.webapp.storage;

import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.SectionType;
import com.basejava.webapp.model.TextSection;

public class ResumeTestData {

    protected static Resume setAllFields(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContacts(ContactType.PHONE, "+7 912 345 6789");
        resume.addContacts(ContactType.SKYPE, "Kislin");
        resume.addSections(SectionType.OBJECTIVE, new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSections(SectionType.PERSONAL, new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        return resume;
    }
}
