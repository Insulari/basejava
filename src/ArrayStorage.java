/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int i = 0;
        while (i < storage.length) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
            if (i == (storage.length - 1)) {
                // Should I throw new exception here?
                System.out.println("not enough array length for saving resume");
            }
            i++;
        }
    }

    Resume get(String uuid) {
        try {
            for (Resume r : storage) {
                if (uuid.equals(r.uuid)) {
                    return r;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Резюме \"" + uuid + "\" не найдено.");
            return null;
        }
        return null;
    }

    void delete(String uuid) {
        int id = 0;
        try {
            for (int i = 0; i < storage.length; i++) {
                if (uuid.equals(storage[i].uuid)) {
                    storage[i] = null;
                    id = i;
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Резюме \"" + uuid + "\" не найдено.");
        }
        // перемещаем последний элемент массива в "дырку".
        int i = id + 1;
        while (i < storage.length) {
            // если был удалён последний элемент массива storage, цикл не выполнится. Всё ОК.
            // доходим до первого нулевого элемента и перемещаем в дырку предшествующий ему элемент
            if (storage[i] == null) {
                if (storage[i-1] == null) break;    // исключает r = r в следующей строке. Если удалили последний инициализированный элемент - уходим.
                storage[id] = storage[i-1];
                storage[i-1] = null;
                break;
            }
            // если дошли до последнего элемента, его в дырку. Проверка на ноль уже была.
            if (i == (storage.length - 1)) {
                storage[id] = storage[i];
                storage [i] = null;
                break;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                size = i;
                break;
            }
        }
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) return i;
        }
        return 0;
    }
}
