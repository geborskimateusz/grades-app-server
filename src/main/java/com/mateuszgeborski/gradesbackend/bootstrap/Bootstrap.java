package com.mateuszgeborski.gradesbackend.bootstrap;

import com.mateuszgeborski.gradesbackend.api.v1.repository.*;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.MessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.*;
import com.mateuszgeborski.gradesbackend.domain.grades.*;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import com.mateuszgeborski.gradesbackend.domain.user.details.ProfileImage;
import com.mateuszgeborski.gradesbackend.domain.user.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {


    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void run(String... args) throws Exception {
        bootstrapGradesObjects();
    }

    private void bootstrapGradesObjects() {

        Classroom classroom = Classroom.testBuilder().name("1B").build();
        Classroom savedClassroom = classroomRepository.save(classroom);

        Subject history =
                Subject.builder()
                        .name("History")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedHistory = subjectRepository.save(history);

        Subject informationTechnology =
                Subject.builder()
                        .name("Information Technology")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedInformationTechnology = subjectRepository.save(informationTechnology);

        Subject science =
                Subject.builder()
                        .name("Science")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedScience = subjectRepository.save(science);

        Subject geography =
                Subject.builder()
                        .name("Goegraphy")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedGeography = subjectRepository.save(geography);

        Subject artAndDesign =
                Subject.builder()
                        .name("Art & Design")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedArtAndDesign = subjectRepository.save(artAndDesign);

        Subject music =
                Subject.builder()
                        .name("Music")
                        .classrooms(Arrays.asList(savedClassroom))
                        .build();
        Subject savedMusic = subjectRepository.save(music);

        classroom.setSubjects(Arrays.asList(savedHistory, savedInformationTechnology, savedScience, savedArtAndDesign, savedMusic));


        Teacher kateMoss = this.bootstrapTeacherKateMoss();
        kateMoss.setSubjects(Arrays.asList(savedScience));
        Teacher savedKateMoss = teacherRepository.save(kateMoss);

        Teacher teacherJohnDoe = this.bootstrapTeacherJohnDoe();
        teacherJohnDoe.setSubjects(Arrays.asList(savedHistory, savedInformationTechnology));
        Teacher savedTeacherJohnDoe = teacherRepository.save(teacherJohnDoe);

        Teacher teacherRagnar = this.bootstrapTeacherRagnar();
        teacherRagnar.setSubjects(Arrays.asList(savedGeography, savedArtAndDesign));
        Teacher savedTeacherRagnar = teacherRepository.save(teacherRagnar);

        Teacher janeDoe = this.bootstrapTeacherJaneDoe();
        janeDoe.setSubjects(Arrays.asList(savedMusic));
        Teacher savedJaneDoe = teacherRepository.save(janeDoe);

        savedHistory.setTeachers(Arrays.asList(savedTeacherJohnDoe));
        savedInformationTechnology.setTeachers(Arrays.asList(savedTeacherJohnDoe));

        savedScience.setTeachers(Arrays.asList(savedKateMoss));

        savedGeography.setTeachers(Arrays.asList(savedTeacherRagnar));
        savedArtAndDesign.setTeachers(Arrays.asList(savedTeacherRagnar));

        savedMusic.setTeachers(Arrays.asList(savedJaneDoe));

        Student student1 = this.bootstrapStudentUser();
        student1.setClassroom(classroom);

        classroom.setStudents(Arrays.asList(student1));

        bulkGrades(savedHistory, savedInformationTechnology, savedScience, savedGeography, savedArtAndDesign, savedMusic, student1);

        bootstrapMessages();
        bootstrapMessages();
        bootstrapMessages();
        bootstrapMessages();
        bootstrapMessages();
        bootstrapMessages();

    }

    private static Integer messageID = 0;

    void bootstrapMessages() {
        User teacher1 = userRepository.findById(1L).get();
        User teacher2 = userRepository.findById(2L).get();
        User teacher3 = userRepository.findById(3L).get();
        User teacher4 = userRepository.findById(4L).get();
        User student = userRepository.findById(5L).get();

        //////////////////////////////////////////////////////

        Message sent1 = SentMessage.builder()
                .title(messageID + "SENT_MESSAGES_DATA_SOURCE Inbox Trip do Italy ")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(teacher1)
                .receiver(student)
                .build();

        Message received1 = ReceivedMessage.builder()
                .title(messageID + "RECEIVED_MESSAGES_DATA_SOURCE Inbox Trip do Italy ")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(student)
                .sender(teacher1)
                .build();

        messageRepository.save(sent1);
        messageRepository.save(received1);
        messageID++;

        //////////////////////////////////////////////////////

        Message sent2 = SentMessage.builder()
                .title(messageID + "SENT_MESSAGES_DATA_SOURCE Inbox Information about additional lectures")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(teacher3)
                .receiver(student)
                .build();

        Message received2 = ReceivedMessage.builder()
                .title(messageID + "RECEIVED_MESSAGES_DATA_SOURCE Inbox Information about additional lectures")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(student)
                .sender(teacher3)
                .build();

        messageRepository.save(sent2);
        messageRepository.save(received2);
        messageID++;

        //////////////////////////////////////////////////////

        Message sent3 = SentMessage.builder()
                .title(messageID +"SENT_MESSAGES_DATA_SOURCE Inbox Insurance Payment")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(student)
                .receiver(teacher3)
                .build();

        Message received3 = ReceivedMessage.builder()
                .title(messageID +"RECEIVED_MESSAGES_DATA_SOURCE Inbox Insurance Payment")
                .details("LorLorem ipsum dolor sit amet consectetur adipisicing elit. Tempore asperiores, omnis ducimus nulla harum numquam sequi doloremque atque voluptatibus accusantium.em")
                .owner(teacher3)
                .sender(teacher3)
                .build();


        messageRepository.save(received3);
        messageRepository.save(sent3);
        messageID++;

    }

    private void bulkGrades(Subject savedHistory,
                            Subject savedInformationTechnology,
                            Subject savedScience,
                            Subject savedGeography,
                            Subject savedArtAndDesign,
                            Subject savedMusic,
                            Student student1) {
        List<Grade> allGrades = this.concat(
                this.bootstrapGradesForHistory(student1, savedHistory),
                this.bootstrapGradesForInformationTechnology(student1, savedInformationTechnology),
                this.bootstrapGradesForScience(student1, savedScience),
                this.bootstrapGradesForGeography(student1, savedGeography),
                this.bootstrapGradesForArtAndDesign(student1, savedArtAndDesign),
                this.bootstrapGradesForMusic(student1, savedMusic)
        );

        student1.setGrades(allGrades);

        //to avoid ConcurrentModificationException
        List<Grade> newGrades = new ArrayList<>(allGrades);
        gradeRepository.saveAll(newGrades);
    }

    private Student bootstrapStudentUser() {
        Role studentRole = Role.builder().name("STUDENT").build();

        User studentuser = User.defaultBuilder()
                .firstName("John")
                .lastName("Doe")
                .motherName("Jane")
                .fatherName("Alan")
                .personalIdentityNum("192837465")
                .dateOfBirth(LocalDate.of(1990, 3, 2))
                .username("user")
                .password("$2a$10$.gv1NAKMjmFYIE3UN4I6gOF2YlxePJl/0J6hlzQr4MKhJ2S1I5zaC")
                .roles(Arrays.asList(studentRole))
                .build();

        Contact studentContact = Contact.builder()
                .email("johny@gmail.com")
                .phoneNumber("321-123-321")
                .user(studentuser)
                .build();


        Address studentAddress = Address.builder()
                .city("Oslo")
                .postalCode("12-435")
                .street("Etvindu")
                .homeNumber("2")
                .user(studentuser)
                .build();

        ProfileImage studentProfileImage = ProfileImage.builder()
                .imageUrl("http://www.seowptheme.com/wp-content/uploads/avatar-2-1.png")
                .user(studentuser)
                .build();


        User saveduser = userRepository.save(studentuser);
        Contact savedStudentContact = contactRepository.save(studentContact);
        Address savedStudentAddress = addressRepository.save(studentAddress);
        ProfileImage savedStudentProfileImage = imageRepository.save(studentProfileImage);

        studentuser.setContact(savedStudentContact);
        studentuser.setAddress(savedStudentAddress);
        studentuser.setProfileImage(savedStudentProfileImage);

        System.out.println(saveduser.getAddress().getCity());
        System.out.println(savedStudentProfileImage.getUser().getFirstName());

        return Student.builder().id(studentuser.getId()).build();
    }

    private Teacher bootstrapTeacherJohnDoe() {
        Role teacherRole = Role.builder().name("TEACHER").build();

        User teacheruser = User.defaultBuilder()
                .firstName("John")
                .lastName("Doe")
                .motherName("Ally")
                .fatherName("Mark")
                .personalIdentityNum("768593645")
                .dateOfBirth(LocalDate.of(1964, 3, 2))
                .username("username2")
                .password("password2")
                .roles(Arrays.asList(teacherRole))
                .build();

        Contact teacherContact = Contact.builder()
                .email("johnydoe@gmail.com")
                .phoneNumber("222-143-321")
                .user(teacheruser)
                .build();


        Address teacherAddress = Address.builder()
                .city("Bergen")
                .postalCode("12-115")
                .street("Smorbrod")
                .homeNumber("4")
                .user(teacheruser)
                .build();

        ProfileImage teacherProfileImage = ProfileImage.builder()
                .imageUrl("http://www.takepart.com/sites/default/files/styles/large/public/black-teacher.jpg")
                .user(teacheruser)
                .build();


        User saveduser = userRepository.save(teacheruser);
        Contact savedTeacherContact = contactRepository.save(teacherContact);
        Address savedTeacherAddress = addressRepository.save(teacherAddress);
        ProfileImage savedTeacherProfileImage = imageRepository.save(teacherProfileImage);

        teacheruser.setContact(savedTeacherContact);
        teacheruser.setAddress(savedTeacherAddress);
        teacheruser.setProfileImage(savedTeacherProfileImage);

        System.out.println(saveduser.getAddress().getCity());
        System.out.println(savedTeacherProfileImage.getUser().getFirstName());

        return Teacher.builder().id(teacheruser.getId()).build();
    }

    private Teacher bootstrapTeacherRagnar() {
        Role teacherRole = Role.builder().name("TEACHER").build();

        User teacheruser = User.defaultBuilder()
                .firstName("Ragnar")
                .lastName("Lothbrok")
                .motherName("Helga")
                .fatherName("Knut")
                .personalIdentityNum("123493645")
                .dateOfBirth(LocalDate.of(1930, 5, 1))
                .username("username3")
                .password("password3")
                .roles(Arrays.asList(teacherRole))
                .build();

        Contact teacherContact = Contact.builder()
                .email("ragnarok@gmail.com")
                .phoneNumber("282-134-721")
                .user(teacheruser)
                .build();


        Address teacherAddress = Address.builder()
                .city("Kattegat")
                .postalCode("02-155")
                .street("Farm")
                .homeNumber("6")
                .user(teacheruser)
                .build();

        ProfileImage teacherProfileImage = ProfileImage.builder()
                .imageUrl("https://edlanta.org/wp-content/uploads/2017/03/1389121022775.jpg")
                .user(teacheruser)
                .build();


        User saveduser = userRepository.save(teacheruser);
        Contact savedTeacherContact = contactRepository.save(teacherContact);
        Address savedTeacherAddress = addressRepository.save(teacherAddress);
        ProfileImage savedTeacherProfileImage = imageRepository.save(teacherProfileImage);

        teacheruser.setContact(savedTeacherContact);
        teacheruser.setAddress(savedTeacherAddress);
        teacheruser.setProfileImage(savedTeacherProfileImage);

        System.out.println(saveduser.getAddress().getCity());
        System.out.println(savedTeacherProfileImage.getUser().getFirstName());

        return Teacher.builder().id(teacheruser.getId()).build();
    }

    private Teacher bootstrapTeacherJaneDoe() {
        Role teacherRole = Role.builder().name("TEACHER").build();

        User teacheruser = User.defaultBuilder()
                .firstName("Jane")
                .lastName("Doe")
                .motherName("Marry")
                .fatherName("Ralf")
                .personalIdentityNum("126666645")
                .dateOfBirth(LocalDate.of(1980, 5, 1))
                .username("username5")
                .password("password5")
                .roles(Arrays.asList(teacherRole))
                .build();

        Contact teacherContact = Contact.builder()
                .email("janeandfame@gmail.com")
                .phoneNumber("989-134-721")
                .user(teacheruser)
                .build();


        Address teacherAddress = Address.builder()
                .city("Chicago")
                .postalCode("04-155")
                .street("Blues Street")
                .homeNumber("7")
                .user(teacheruser)
                .build();

        ProfileImage teacherProfileImage = ProfileImage.builder()
                .imageUrl("https://www.americancollegespain.com/wp-content/uploads/2018/10/bigstock-pretty-teacher-smiling-at-came-69887626.jpg")
                .user(teacheruser)
                .build();


        User saveduser = userRepository.save(teacheruser);
        Contact savedTeacherContact = contactRepository.save(teacherContact);
        Address savedTeacherAddress = addressRepository.save(teacherAddress);
        ProfileImage savedTeacherProfileImage = imageRepository.save(teacherProfileImage);

        teacheruser.setContact(savedTeacherContact);
        teacheruser.setAddress(savedTeacherAddress);
        teacheruser.setProfileImage(savedTeacherProfileImage);

        System.out.println(saveduser.getAddress().getCity());
        System.out.println(savedTeacherProfileImage.getUser().getFirstName());

        return Teacher.builder().id(teacheruser.getId()).build();
    }

    private Teacher bootstrapTeacherKateMoss() {
        Role teacherRole = Role.builder().name("TEACHER").build();

        User teacheruser = User.defaultBuilder()
                .firstName("Kate")
                .lastName("Moss")
                .motherName("Kate")
                .fatherName("Adam")
                .personalIdentityNum("126624526")
                .dateOfBirth(LocalDate.of(1980, 5, 1))
                .username("username4")
                .password("password4")
                .roles(Arrays.asList(teacherRole))
                .build();

        Contact teacherContact = Contact.builder()
                .email("moss@gmail.com")
                .phoneNumber("428-134-721")
                .user(teacheruser)
                .build();


        Address teacherAddress = Address.builder()
                .city("Los Angeles")
                .postalCode("04-155")
                .street("Stars")
                .homeNumber("7")
                .user(teacheruser)
                .build();

        ProfileImage teacherProfileImage = ProfileImage.builder()
                .imageUrl("https://i.cbc.ca/1.4175991.1498250645!/fileImage/httpImage/image.jpg_gen/derivatives/16x9_780/bad-teacher.jpg")
                .user(teacheruser)
                .build();


        User saveduser = userRepository.save(teacheruser);
        Contact savedTeacherContact = contactRepository.save(teacherContact);
        Address savedTeacherAddress = addressRepository.save(teacherAddress);
        ProfileImage savedTeacherProfileImage = imageRepository.save(teacherProfileImage);

        teacheruser.setContact(savedTeacherContact);
        teacheruser.setAddress(savedTeacherAddress);
        teacheruser.setProfileImage(savedTeacherProfileImage);

        System.out.println(saveduser.getAddress().getCity());
        System.out.println(savedTeacherProfileImage.getUser().getFirstName());

        return Teacher.builder().id(teacheruser.getId()).build();
    }

    private List<Grade> bootstrapGradesForHistory(Student student, Subject subject) {
        Grade a = Grade.builder()
                .letter("A")
                .topic("For exam about ancient gods")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();


        Grade f = Grade.builder()
                .letter("F")
                .topic("For exam about vikings")
                .dateOfIssue(LocalDate.of(2015, 1, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b = Grade.builder()
                .letter("B")
                .topic("For exam about medieval wars")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b1 = Grade.builder()
                .letter("B")
                .topic("For exam about medieval swords")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade cplus = Grade.builder()
                .letter("C+")
                .topic("For exam about world war II")
                .dateOfIssue(LocalDate.of(2015, 3, 12))
                .student(student)
                .subject(subject)
                .build();

        return Arrays.asList(a, f, b, b1, cplus);
    }

    private List<Grade> bootstrapGradesForInformationTechnology(Student student, Subject subject) {
        Grade a = Grade.builder()
                .letter("A")
                .topic("For exam about hard drives")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();


        Grade f = Grade.builder()
                .letter("F")
                .topic("For Data Structures and Algorithms")
                .dateOfIssue(LocalDate.of(2015, 1, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b = Grade.builder()
                .letter("B")
                .topic("For building html page")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b1 = Grade.builder()
                .letter("B")
                .topic("For preparing talk about JavaScript frameworks ")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade cplus = Grade.builder()
                .letter("C+")
                .topic("For exam about CISCO systems")
                .dateOfIssue(LocalDate.of(2015, 3, 12))
                .student(student)
                .subject(subject)
                .build();

        Grade b2 = Grade.builder()
                .letter("B")
                .topic("For student activity")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b11 = Grade.builder()
                .letter("B")
                .topic("For student activity")
                .dateOfIssue(LocalDate.of(2015, 5, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade cplusplus = Grade.builder()
                .letter("C+")
                .topic("For student activity")
                .dateOfIssue(LocalDate.of(2015, 3, 12))
                .student(student)
                .subject(subject)
                .build();

        return Arrays.asList(cplusplus, b11, b2, cplus, b, b1, a, f);
    }

    private List<Grade> bootstrapGradesForScience(Student student, Subject subject) {
        Grade dplus = Grade.builder()
                .letter("D+")
                .topic("Test about atoms")
                .dateOfIssue(LocalDate.of(2015, 12, 1))
                .student(student)
                .subject(subject)
                .build();

        Grade c = Grade.builder()
                .letter("C")
                .topic("Homework exercises")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();


        return Arrays.asList(dplus, c);
    }

    private List<Grade> bootstrapGradesForGeography(Student student, Subject subject) {
        Grade a = Grade.builder()
                .letter("A")
                .topic("Test about Rome.")
                .dateOfIssue(LocalDate.of(2015, 12, 1))
                .student(student)
                .subject(subject)
                .build();

        Grade a1 = Grade.builder()
                .letter("A")
                .topic("Test about animals")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b = Grade.builder()
                .letter("B")
                .topic("Test about Africa")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade a2 = Grade.builder()
                .letter("A")
                .topic("Test about scale and proportions.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade a3 = Grade.builder()
                .letter("A")
                .topic("Test about geology..")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade d = Grade.builder()
                .letter("D")
                .topic("Test about sea and ocean")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();


        return Arrays.asList(a, a1, b, a2, a3, d);
    }

    private List<Grade> bootstrapGradesForArtAndDesign(Student student, Subject subject) {
        Grade a = Grade.builder()
                .letter("A")
                .topic("Paperwork.")
                .dateOfIssue(LocalDate.of(2015, 12, 1))
                .student(student)
                .subject(subject)
                .build();

        Grade a1 = Grade.builder()
                .letter("F")
                .topic("Paperwork 2.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b = Grade.builder()
                .letter("B")
                .topic("Test about modern clothes.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade a2 = Grade.builder()
                .letter("D")
                .topic("Test about architecture.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        return Arrays.asList(a, a1, b, a2);
    }

    private List<Grade> bootstrapGradesForMusic(Student student, Subject subject) {
        Grade a = Grade.builder()
                .letter("A")
                .topic("Test about medival music.")
                .dateOfIssue(LocalDate.of(2015, 12, 1))
                .student(student)
                .subject(subject)
                .build();

        Grade a1 = Grade.builder()
                .letter("C")
                .topic("Test about Bach.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade b = Grade.builder()
                .letter("C")
                .topic("Test about Vikings folk music.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        Grade a2 = Grade.builder()
                .letter("C")
                .topic("For singing a song.")
                .dateOfIssue(LocalDate.of(2015, 3, 2))
                .student(student)
                .subject(subject)
                .build();

        return Arrays.asList(a, a1, b, a2);
    }

    public <T> List<T> concat(List<T>... lists) {
        return Stream.of(lists).flatMap(List::stream).collect(Collectors.toList());
    }
}