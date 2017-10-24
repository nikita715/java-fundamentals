||Correct|Not correct|
|-|-|-|
|`Doctor doctor1 = new Doctor();`|&#10003;
|`Doctor doctor2 = new MedicalStaff();`||	&#10003;
|`Doctor doctor3 = new HeadDoctor();`|	&#10003;
|`Object object1 = new HeadDoctor();`|	&#10003;
|`HeadDoctor doctor5 = new Object();`||	&#10003;
|`Doctor doctor6 = new Nurse();`||	&#10003;
|`Nurse nurse = new Doctor();`||	&#10003;
|`Object object2 = new Nurse();`|	&#10003;
||
|`List<Doctor> list1= new ArrayList<Doctor>();`|	&#10003;
|`List<MedicalStaff> list2 = new ArrayList<Doctor>();`||	&#10003;
|`List<Doctor> list3 = new ArrayList<MedicalStaff>();`||	&#10003;
|`List<Object> list4 = new ArrayList<Doctor>();`||	&#10003;
|`List<Object> list5 = new ArrayList<Object>();`|	&#10003;

### Explanation:
- Link to a specific type can only refer to objects that have the same class or any of its subclasses.
- Links with generic type can only refer to objects that have the same generic type. The only possibility to refer to types 
which are its subclasses is to use construction `<? extends type>`. In order to refer to the generic superclass you can use the construction `<? super type>`.
