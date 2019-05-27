
typedef struct _jarAttribute {
    char* name;
    char* value;
    struct _jarAttribute* next;
} jarAttribute;


/* Returns a list of attributes */
jarAttribute* readAttributes(const char* jarfile);

/* Frees attribute list */
void freeAttributes(jarAttribute* attributes);

/* Gets the attribute by name */
char* getAttribute(const jarAttribute* attributes, const char* name);
