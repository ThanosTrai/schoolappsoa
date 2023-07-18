package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    /**
     * Inserts a {@link Teacher} based on the data carried by the
     * {@link TeacherInsertDTO}.
     *
     *
     * @param teacherToInsert
     *          DTO object that contains the data.
     * @return
     *          The inserted teacher instance.
     * @throws TeacherDAOException
     *          if any DAO exception happens.
     */
    Teacher insertTeacher(TeacherInsertDTO teacherToInsert) throws TeacherDAOException;

    /**
     * Updates a {@link Teacher} based on the data carried by the
     * {@link TeacherUpdateDTO};
     *
     * @param dto
     *          DTO object that contains the data
     *          of the new {@link Teacher}.
     * @return
     *          the update instance of the {@link Teacher}.
     * @throws TeacherDAOException
     *          if any DAO exception happens.
     * @throws TeacherNotFoundException
     *          if any Teacher identified by their id
     *          was not found.
     */
    Teacher updateTeacher(TeacherUpdateDTO teacherToUpdate) throws TeacherDAOException, TeacherNotFoundException;

    /**
     * Deletes a {@link Teacher} based on the data carried by the
     * {@link Teacher id}.
     *
     * @param id
     * @throws TeacherDAOException
     * @throws TeacherNotFoundException
     */
    void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException;

    /**
     *
     * @param lastname
     * @return
     * @throws TeacherDAOException
     */
    List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException;

    /**
     *
     * @param id
     * @return
     * @throws TeacherDAOException
     * @throws TeacherNotFoundException
     */
    Teacher getTeacherById(int id) throws TeacherDAOException, TeacherNotFoundException;
}
