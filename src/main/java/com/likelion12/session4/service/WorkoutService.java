package com.likelion12.session4.service;

import com.likelion12.session4.dto.WorkoutInput;
import com.likelion12.session4.dto.WorkoutOutput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.likelion12.session4.dto.WorkoutPartOutput;
import com.likelion12.session4.model.Workout;
import com.likelion12.session4.repository.WorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 * 임시로 만든 운동기록 서비스.
 */
@Service
public class WorkoutService {
    public static final List<Workout> workoutsRepository = new ArrayList<>();      // 임시 데이터베이스
    private final WorkoutRepository workoutRepository;
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }
    /**
     * 운동기록 저장
     * @param workoutInput 저장할 데이터 정보
     * @return WorkoutOuput: 저장한 workout 기록을 출력
     */
    public WorkoutOutput create(WorkoutInput workoutInput){
        // workoutInput(유저에게 받은 데이터 객체) -> workout[newData](데이터베이스에 저장될 객체)
        Workout newData = new Workout();
//        newData.id = getNextId();
        newData.date = LocalDateTime.now();
        newData.exerciseName = workoutInput.getExerciseName();
        newData.duration = workoutInput.getDuration();
        newData.content = workoutInput.getContent();

        // newData 저장 및 저장된 객체 리턴
        workoutRepository.save(newData);
        return newData.toWorkoutOutput();
    }

    /**
     * 저장되어 있는 모든 운동기록을 리턴
     * @return WorkoutOutput List
     */
    public List<WorkoutOutput> findAll(){
        // 데이터베이스의 모든 정보를 읽고 유저에게 리턴할 데이터(workoutOutput)로 맞춰 변환해 리턴
        Iterable<Workout> workouts = workoutRepository.findAll();
        List<WorkoutOutput> result = new ArrayList<>();
        for(Workout workout: workouts){
            result.add(workout.toWorkoutOutput());
        }
        return result;
    }

    /**
     * 페이지 단위로 운동기록을 리턴
     *
     * @param howMany 한 페이지에 들어가는 운동기록의 수
     * @param pageNum 페이지번호
     * @return WorkoutOutput List
     */
    public WorkoutPartOutput findPart(Integer howMany, Integer pageNum){
        List<WorkoutOutput> result = new ArrayList<>();

        int endNum = howMany*pageNum + howMany;
        for(int i=endNum - howMany;i < endNum && i < workoutsRepository.size(); i++){
            result.add(workoutsRepository.get(i).toWorkoutOutput());
        }
        return new WorkoutPartOutput(
                result,
                howMany==0 ? 0 : workoutsRepository.size()/howMany + (workoutsRepository.size()%howMany==0 ? 0:1)
        );
    }

    /**
     * 운동기록 수정
     * @param id 수정될 운동기록의 id
     * @param workoutInput 수정에 적용할 내용
     * @return 수정된 운동기록
     */
    public WorkoutOutput update(Long id, WorkoutInput workoutInput){
        // id에 해당하는 운동기록 조회
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        Workout workout = optionalWorkout.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not found"));
        // id에 해당하는 workout을 찾을 수 없을 때

        // 수정될 내용 적용 및 리턴
        if (workoutInput.getExerciseName() != null) {
            

        }
        if (workoutInput.getDuration() != null) {

        }
        if (workoutInput.getContent() != null) {

        }

        workoutRepository.save(workout);
        return workout.toWorkoutOutput();
    }

    /**
     * 운동기록 삭제
     * @param id 삭제될 운동기록의 id
     */
    public void delete(Long id){
        // id에 해당하는 운동기록 조회
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        Workout workout = optionalWorkout.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " not found"));

        workoutRepository.delete(workout);
    }

    private long getNextId(){
        if(workoutsRepository.isEmpty()) return 0;

        Workout lastData = workoutsRepository.get(workoutsRepository.size()-1);
        return lastData.id+1;
    }
    private Workout findById(Long id){
        for(Workout i: workoutsRepository){
            if(id.equals(i.id)){
                return i;
            }
        }
        return null;
    }

    /**
     * 데이터베이스에 저장될 객체
     * (Entity라고 불리며 일반적으로 model 폴더 내에 존재)
     */
}

