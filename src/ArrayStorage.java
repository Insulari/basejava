/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) break;
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else System.out.println("Not enough array length for saving resume");
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
        if (size == 0) System.out.println("Операция не выполнена. Нет элементов для удаления.");
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                if (i != (size - 1)) storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else System.out.println("Резюме \"" + uuid + "\" не найдено.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

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
        return size;
    }
}