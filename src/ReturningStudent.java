import java.util.*;

public class ReturningStudent {
	
	private StudentListContainer container;
	
	public ReturningStudent () {
		
		container = StudentListContainer.getInstance();
		
	}
	
	public void selectNewSubs() {
		
		ArrayList <StudentDetails> studentList = container.getStudentList();
		
		ArrayList<CourseDetails> prevCourses = studentList.get(container.getMarker()).getCurrentCourseList();
		ArrayList<CourseDetails> prevCourses2 = studentList.get(container.getMarker()).getAllCourseList();
		ArrayList<CourseDetails> newCourses = new ArrayList<>();
		
		for(CourseDetails course : prevCourses) 
			if(course.getCourseGrade() > 3.00)
				studentList.get(container.getMarker()).setScholStatus("IRREGULAR");
		
		
		if(studentList.get(container.getMarker()).getStudYear() == 1 &&
				studentList.get(container.getMarker()).getStudSem() == 1)
		{
			CourseDetails course1, course2;
			
			course1 = new CourseDetails("CC103", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			course2 = new CourseDetails("CS123", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			newCourses.add(course1);
			newCourses.add(course2);

		} 
		else if (studentList.get(container.getMarker()).getStudYear() == 1 &&
				studentList.get(container.getMarker()).getStudSem() == 2) 
		{
			CourseDetails course1, course2, course3, course4;
			
			course1 = new CourseDetails("CS251L", "<DAY> <TIME SLOT>", "FACULTY NAME",
											1, 0.00);
			course2 = new CourseDetails("CS252", "<DAY> <TIME SLOT>", "FACULTY NAME",
											2, 0.00);
			course3 = new CourseDetails("CS271L", "<DAY> <TIME SLOT>", "FACULTY NAME",
											2, 0.00);
			course4 = new CourseDetails("CS272", "<DAY> <TIME SLOT>", "FACULTY NAME",
											2, 0.00);
			
			newCourses.add(course1);
			newCourses.add(course2);
			newCourses.add(course3);
			newCourses.add(course4);
			
		}
		else if (studentList.get(container.getMarker()).getStudYear() == 2 &&
				studentList.get(container.getMarker()).getStudSem() == 1)
		{
			CourseDetails course1;
			
			course1 = new CourseDetails("MATHSTAT03", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			
			newCourses.add(course1);
			
		} 
		else if (studentList.get(container.getMarker()).getStudYear() == 2 &&
				studentList.get(container.getMarker()).getStudSem() == 2)
		{
			CourseDetails course1, course2;
			
			course1 = new CourseDetails("CSE1", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			course2 = new CourseDetails("CSE2", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			
			newCourses.add(course1);
			newCourses.add(course2);
			
		}
		else if (studentList.get(container.getMarker()).getStudYear() == 3 &&
				studentList.get(container.getMarker()).getStudSem() == 1)
		{
			CourseDetails course1, course2, course3, course4;
			
			double cg1 = 0.00;
			
			for(CourseDetails course : prevCourses2) {// CS233 grade
				String courseCode = course.getCourseCode();
				if(courseCode.equals("CS352")) {
					cg1 = course.getCourseGrade();
					break;
				}
			}
			
			if(cg1 <= 3.00) {
			
				course1 = new CourseDetails("CS303", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);			
				
				newCourses.add(course1);
				
				
			}
			
			course2 = new CourseDetails("CC303", "<DAY> <TIME SLOT>", "FACULTY NAME",
										3, 0.00);
			course3 = new CourseDetails("CSE3", "<DAY> <TIME SLOT>", "FACULTY NAME",
										3, 0.00);
			course4 = new CourseDetails("CSE4", "<DAY> <TIME SLOT>", "FACULTY NAME",
										3, 0.00);
			
			newCourses.add(course2);
			newCourses.add(course3);
			newCourses.add(course4);
			
		}
		else if (studentList.get(container.getMarker()).getStudYear() == 3 &&
				studentList.get(container.getMarker()).getStudSem() == 2)
		{
			CourseDetails course1, course2;
			
			course1 = new CourseDetails("CS413", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			course2 = new CourseDetails("CS433", "<DAY> <TIME SLOT>", "FACULTY NAME",
											3, 0.00);
			newCourses.add(course1);
			newCourses.add(course2);
			
		} else if (studentList.get(container.getMarker()).getStudYear() == 4 &&
				studentList.get(container.getMarker()).getStudSem() == 1)
		{
			CourseDetails course1;
			
			course1 = new CourseDetails("CS403", "<DAY> <TIME SLOT>", "FACULTY NAME",
											6, 0.00);

			newCourses.add(course1);
			
		} 
		
		
		for(CourseDetails course : prevCourses) {
			String courseCode = course.getCourseCode();
			double courseGrade = course.getCourseGrade();
			
			// 1st Year - 1st Semester
			if(courseCode.equals("CC131L")) {
				
				CourseDetails course1, course2;
				
				double cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses)
					if(courseCode.equals("CC132")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					
					course1 = new CourseDetails("CC141L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC142", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					
				}
				else
				{
					
					course1 = new CourseDetails("CC131L", "<DAY> <TIME SLOT>", "FACULTY NAME",
							1, 0.00);
					course2 = new CourseDetails("CC132", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					
				}
				
				newCourses.add(course1);
				newCourses.add(course2);
							
			} else if (courseCode.equals("MATHA05S")) {
				
				CourseDetails course1;
				
				if(courseGrade <= 3.00) 
					course1 = new CourseDetails("MATHA35", "<DAY> <TIME SLOT>", "FACULTY NAME",
												5, 0.00);
				else
					course1 = new CourseDetails("MATHA05S", "<DAY> <TIME SLOT>", "FACULTY NAME",
												5, 0.00);
				
				newCourses.add(course1);
				
			} else if (courseCode.equals("CC113")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CC113", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					
				}
				
			}
			// 1st Year - 2nd Semester
			else if (courseCode.equals("CC141L"))
			{
				CourseDetails course1, course2, course3;
				
				double cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses)
					if(courseCode.equals("CC142")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					course1 = new CourseDetails("CC211L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC212", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					course3 = new CourseDetails("CS213", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					newCourses.add(course3);
					
				}
				else
				{
					course1 = new CourseDetails("CC141L", "<DAY> <TIME SLOT>", "FACULTY NAME",
							1, 0.00);
					course2 = new CourseDetails("CC142", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					
				}
			} else if (courseCode.equals("CC103")) {
				
				CourseDetails course1;
				
				if(courseGrade <= 3.00) 
					course1 = new CourseDetails("CS233", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);			
				else
					course1 = new CourseDetails("CC103", "<DAY> <TIME SLOT>", "FACULTY NAME",
												5, 0.00);
				
				newCourses.add(course1);
				
			} else if (courseCode.equals("CS123")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS123", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					
				}
				
			} else if (courseCode.equals("MATHA35")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("MATHA35", "<DAY> <TIME SLOT>", "FACULTY NAME",
												5, 0.00);
					newCourses.add(course1);
					
				}
				
			}
			// 2nd Year - 1st Semester
			else if (courseCode.equals("CC211L")) {
				CourseDetails course1, course2, course3;
				
				double cg1, cg2, cg3;
				cg1 = cg2 = cg3 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CC212 grade
					if(courseCode.equals("CC212")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses) // CS271L grade
					if(courseCode.equals("CS271L")) {
						cg2 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses) // CS272 grade
					if(courseCode.equals("CS272")) {
						cg3 = course_2.getCourseGrade();
						break;
					}
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					course1 = new CourseDetails("CC201L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC202", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					course3 = new CourseDetails("CS243", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					newCourses.add(course3);
					
					if(cg2 <= 3.00 && cg3 <= 3.00) {
						course1 = new CourseDetails("CS201L", "<DAY> <TIME SLOT>", "FACULTY NAME",
														1, 0.00);
						course2 = new CourseDetails("CS202", "<DAY> <TIME SLOT>", "FACULTY NAME",
														2, 0.00);
						newCourses.add(course1);
						newCourses.add(course2);
						
					}
				}
				else
				{
					course1 = new CourseDetails("CC211L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC212", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					
				}
			} else if (courseCode.equals("CS251L")) {
				CourseDetails course1, course2;
				
				double cg1, cg2;
				cg1 = cg2 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS252 grade
					if(courseCode.equals("CS252")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses) // CS213 grade
					if(courseCode.equals("CS213")) {
						cg2 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					course1 = new CourseDetails("CS221L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS222", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					
					newCourses.add(course1);
					newCourses.add(course2);
					
					if(cg2 <= 3.00) {
						course1 = new CourseDetails("CC223", "<DAY> <TIME SLOT>", "FACULTY NAME",
														3, 0.00);
						newCourses.add(course1);
						
					}
				}
				else
				{
					course1 = new CourseDetails("CC251L", "<DAY> <TIME SLOT>", "FACULTY NAME",
							1, 0.00);
					course2 = new CourseDetails("CC252", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					
				}
			} else if (courseCode.equals("CS271L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS272 grade
					if(courseCode.equals("CS272")) { 
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					course1 = new CourseDetails("CS261L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS262", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					
					newCourses.add(course1);
					newCourses.add(course2);
					
				}
				else
				{
					course1 = new CourseDetails("CS271L", "<DAY> <TIME SLOT>", "FACULTY NAME",
							1, 0.00);
					course2 = new CourseDetails("CS272", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);
					
				}
			} else if (courseCode.equals("CS213")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS213", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CS233")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS233", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			}
			// 2nd Year - 2nd Semester
			else if (courseCode.equals("CC201L")) {
				CourseDetails course1, course2;
				
				double cg1, cg2, cg3, cg4;
				cg1 = cg2 = cg3 = cg4 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CC202 grade
					if(courseCode.equals("CC202")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses2) // CS251L grade
					if(courseCode.equals("CS251L")) {
						cg2 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses2) // CS252 grade
					if(courseCode.equals("CS252")) {
						cg3 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses) // MATHSTAT03 grade
					if(courseCode.equals("MATHSTAT03")) {
						cg4 = course_2.getCourseGrade();
						break;
					}
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					course1 = new CourseDetails("CC311L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC312", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);

					newCourses.add(course1);
					newCourses.add(course2);
					
					if(cg2 <= 3.00 && cg3 <= 3.00) {
						course1 = new CourseDetails("CS351L", "<DAY> <TIME SLOT>", "FACULTY NAME",
														1, 0.00);
						course2 = new CourseDetails("CS352", "<DAY> <TIME SLOT>", "FACULTY NAME",
														2, 0.00);
						newCourses.add(course1);
						newCourses.add(course2);
						
					}
					
					if(cg4 <= 3.00) {
						course1 = new CourseDetails("CS333", "<DAY> <TIME SLOT>", "FACULTY NAME",
														3, 0.00);
						newCourses.add(course1);

					}
				}
				else
				{
					course1 = new CourseDetails("CC201L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC202", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
			} else if (courseCode.equals("CS201L")) {
				CourseDetails course1, course2;
				
				double cg1, cg2;
				cg1 = cg2 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS202 grade
					if(courseCode.equals("CS202")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				for(CourseDetails course_2 : prevCourses) // CC223 grade
					if(courseCode.equals("CC223")) {
						cg2 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 <= 3.00 && cg2 <= 3.00 && courseGrade <= 3.00) {
					
					course1 = new CourseDetails("CS373", "<DAY> <TIME SLOT>", "FACULTY NAME",
													3, 0.00);

					newCourses.add(course1);

				}
				else
				{
					course1 = new CourseDetails("CS201L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS202", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
			} else if (courseCode.equals("CS261L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS262 grade
					if(courseCode.equals("CS262")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					
					course1 = new CourseDetails("CS313", "<DAY> <TIME SLOT>", "FACULTY NAME",
													3, 0.00);

					newCourses.add(course1);

				}
				else
				{
					course1 = new CourseDetails("CS261L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS262", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
			} else if (courseCode.equals("CS221L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS222 grade
					if(courseCode.equals("CS222")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 > 3.00 && courseGrade > 3.00) {
					
					course1 = new CourseDetails("CS221L", "<DAY> <TIME SLOT>", "FACULTY NAME",
													1, 0.00);
					course2 = new CourseDetails("CS222", "<DAY> <TIME SLOT>", "FACULTY NAME",
													2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				
			} else if (courseCode.equals("CS243")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS243", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CC223")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CC223", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("MATHSTAT03")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("MATHSTAT03", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			}
			// 3rd Year - 1st Semester
			else if (courseCode.equals("CS351L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CS352 grade
					if(courseCode.equals("CS352")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 <= 3.00 && courseGrade <= 3.00) {
					
					course1 = new CourseDetails("CS361L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS362", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				else
				{
					course1 = new CourseDetails("CS351L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS352", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				
			} else if (courseCode.equals("CS333")) {
				
				CourseDetails course1, course2, course3;
				
				if(courseGrade <= 3.00) {
					
					course1 = new CourseDetails("CS321L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CS322", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					course3 = new CourseDetails("CS343", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					
					newCourses.add(course1);
					newCourses.add(course2);
					newCourses.add(course3);
					
				} else {
					
					course1 = new CourseDetails("CS", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					
				}
				 
			} else if (courseCode.equals("CSE1")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CSE1", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					
				}
				
			} else if (courseCode.equals("CSE2")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CSE2", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CS373")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS373", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CS313")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS313", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CC311L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CC312 grade
					if(courseCode.equals("CC312")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 > 3.00 && courseGrade > 3.00) {
					
					course1 = new CourseDetails("CC311L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC312", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				
			} 
			// 3rd Year - 2nd Semester
			else if (courseCode.equals("CC321L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CC312 grade
					if(courseCode.equals("CC322")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 > 3.00 && courseGrade > 3.00) {
					
					course1 = new CourseDetails("CC321L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC322", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				
			} else if (courseCode.equals("CC361L")) {
				CourseDetails course1, course2;
				
				double cg1;
				cg1 = 0.00;
				
				for(CourseDetails course_2 : prevCourses) // CC362 grade
					if(courseCode.equals("CC362")) {
						cg1 = course_2.getCourseGrade();
						break;
					}
				
				
				if(cg1 > 3.00 && courseGrade > 3.00) {
					
					course1 = new CourseDetails("CC361L", "<DAY> <TIME SLOT>", "FACULTY NAME",
												1, 0.00);
					course2 = new CourseDetails("CC362", "<DAY> <TIME SLOT>", "FACULTY NAME",
												2, 0.00);
					newCourses.add(course1);
					newCourses.add(course2);

				}
				
			} else if (courseCode.equals("CC303")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CC303", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CS303")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS303", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CS343")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS343", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CSE3")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CSE3", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			} else if (courseCode.equals("CSE4")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CSE4", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
					
				}
				
			}
			// 4th Year - 1st Semester
			else if (courseCode.equals("CS413")) {
				
				CourseDetails course1;
				
				if(courseGrade <= 3.00) {
					course1 = new CourseDetails("CS423", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				} 
				else 
				{
					course1 = new CourseDetails("CS413", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);
				}
				
			} else if (courseCode.equals("CS433")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS433", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			}
			// 4th Year - 2nd Semester
			else if (courseCode.equals("CS403")) {
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS403", "<DAY> <TIME SLOT>", "FACULTY NAME",
												6, 0.00);
					newCourses.add(course1);

				}
				
			}
			else  
			{
				
				CourseDetails course1;
				
				if(courseGrade > 3.00) {
					course1 = new CourseDetails("CS433", "<DAY> <TIME SLOT>", "FACULTY NAME",
												3, 0.00);
					newCourses.add(course1);

				}
				
			}

				
		}
		
		studentList.get(container.getMarker()).getCurrentCourseList().clear();
		for (CourseDetails newCourse : newCourses) {

		    studentList.get(container.getMarker()).getCurrentCourseList().add(new CourseDetails(newCourse.getCourseCode(), newCourse.getCourseSched(),
		    																 newCourse.getCourseFaculty(), newCourse.getCourseUnits(), newCourse.getCourseGrade()));
		    
		}

		studentList.get(container.getMarker()).getAllCourseList().addAll(newCourses);
		
		container.removeDupCourses();
		
		if(studentList.get(container.getMarker()).getStudSem() == 1) {
			
			studentList.get(container.getMarker()).setStudSem(2);
			
		}
		else 
		{
			if(studentList.get(container.getMarker()).getStudYear() < 4) {
				
				int studYear = studentList.get(container.getMarker()).getStudYear() + 1;
				
				studentList.get(container.getMarker()).setStudYear(studYear);	
				studentList.get(container.getMarker()).setStudSem(1);
				
			}
		}
			
	}
    
}
