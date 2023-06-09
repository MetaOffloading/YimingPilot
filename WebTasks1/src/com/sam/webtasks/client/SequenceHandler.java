//The SequenceHandler is the piece of code that defines the sequence of events
//that constitute the experiment.
//
//SequenceHandler.Next() will run the next step in the sequence.
//
//We can also switch between the main sequence of events and a subsequence
//using the SequenceHandler.SetLoop command. This takes two inputs:
//The first sets which loop we are in. 0 is the main loop. 1 is the first
//subloop. 2 is the second subloop, and so on.
//
//The second input is a Boolean. If this is set to true we initialise the 
//position so that the sequence will start from the beginning. If it is
//set to false, we will continue from whichever position we were currently in.
//
//So SequenceHandler.SetLoop(1,true) will switch to the first subloop,
//starting from the beginning.
//
//SequenceHandler.SetLoop(0,false) will switch to the main loop,
//continuing from where we left off.

//TODO:
//scroll
//data output
//resume where you left off

package com.sam.webtasks.client;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sam.webtasks.basictools.CheckIdExists;
import com.sam.webtasks.basictools.CheckScreenSize;
import com.sam.webtasks.basictools.ClickPage;
import com.sam.webtasks.basictools.Consent;
import com.sam.webtasks.basictools.Counterbalance;
import com.sam.webtasks.basictools.InfoSheet;
import com.sam.webtasks.basictools.Initialise;
import com.sam.webtasks.basictools.Names;
import com.sam.webtasks.basictools.PHP;
import com.sam.webtasks.basictools.ProgressBar;
import com.sam.webtasks.basictools.Slider;
import com.sam.webtasks.basictools.TimeStamp;
import com.sam.webtasks.iotask1.IOtask1Block;
import com.sam.webtasks.iotask1.IOtask1BlockContext;
import com.sam.webtasks.iotask1.IOtask1InitialiseTrial;
import com.sam.webtasks.iotask1.IOtask1RunTrial;
import com.sam.webtasks.iotask2.IOtask2Block;
import com.sam.webtasks.iotask2.IOtask2BlockContext;
import com.sam.webtasks.iotask2.IOtask2RunTrial;
import com.sam.webtasks.perceptualTask.PerceptBlock;
import com.sam.webtasks.timeBasedOffloading.TimeBlock;
import com.sam.webtasks.iotask2.IOtask2InitialiseTrial;
import com.sam.webtasks.iotask2.IOtask2PreTrial;
import com.sam.webtasks.iotask2.IOtask2ChoiceOverwrite;

public class SequenceHandler {
	public static void Next() {	
		// move forward one step in whichever loop we are now in
		sequencePosition.set(whichLoop, sequencePosition.get(whichLoop) + 1);

		switch (whichLoop) {
		case 0: // MAIN LOOP
			switch (sequencePosition.get(0)) {
			/***********************************************************************
			 * The code here defines the main sequence of events in the experiment *
			 **********************************************************************/
			//case 1:
			//String startdata = TimeStamp.Now() + ",";
			//startdata = startdata + SessionInfo.participantID;
			
			//PHP.logData("start", startdata, true);
			//break;
			case 1:
				ClickPage.Run(Instructions.Get(1), "Next");
				break;					
			case 2:
				IOtask2Block block1 = new IOtask2Block();
				
				block1.totalCircles = 10;
				block1.nTargets = 0;
				block1.blockNum = 1;
				block1.logDragData = true;
				block1.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");

				block1.Run();
				break;
			case 3:
				ClickPage.Run(Instructions.Get(2), "Next");
				break;
			case 4:
				IOtask2Block block2 = new IOtask2Block();
				
				block2.totalCircles = 10;
				block2.nTargets = 3;
				block2.offloadCondition = Names.REMINDERS_NOTALLOWED;
				block2.blockNum = 2;
				block2.logDragData = true;
				block2.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block2.Run();
				break;
			case 5:
				if (IOtask2BlockContext.getnHits() == 0) { 
					SequenceHandler.SetPosition(SequenceHandler.GetPosition()-2); //this line means that instead of moving forward we will repeat the previous instructions
					ClickPage.Run("You did not drag any special circles to the instructed side.", "Try again");
				} else {
					SequenceHandler.Next(); //move to the next instruction
				}
				break;
			case 6:
				ClickPage.Run(Instructions.Get(3), "Next");
				break;
			case 7:
				IOtask2Block block3 = new IOtask2Block();
				
				block3.nTargets = 6;
				block3.totalCircles = 15;
				block3.offloadCondition = Names.REMINDERS_NOTALLOWED;
				block3.blockNum = 3;
				block3.logDragData = true;
				block3.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block3.Run();
				break;
			case 8:
				Slider.Run(Instructions.Get(4), "0%", "100%");
				break;
			case 9:
				PHP.logData("predictionInt1", ""+Slider.getSliderValue(), true);
				break;
			case 10:
				ClickPage.Run(Instructions.Get(5), "Next");
				break;
			case 11:
				IOtask2Block block4 = new IOtask2Block();
				
				block4.nTargets = 6;
				block4.totalCircles = 15;
				block4.offloadCondition = Names.REMINDERS_MANDATORY_TARGETONLY;
				block4.blockNum = 4;
				block4.logDragData = true;
				block4.showTargetFeedback = false;
				block4.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block4.Run();
				break;	
			case 12:
				Slider.Run(Instructions.Get(51), "0%", "100%");
				break;
			case 13:
				PHP.logData("predictionExt1",  ""+Slider.getSliderValue(), true);
				break;
			case 14:
				ClickPage.Run(Instructions.Get(6), "Next");
				break;
			case 15:
				ClickPage.Run(Instructions.Get(61), "Next");
				break;
			case 16:
				ClickPage.Run(Instructions.Get(62), "Next");
				break;
			case 17:
				IOtask2Block block5 = new IOtask2Block();
				
				block5.nTargets = 6;
				block5.totalCircles = 15;
				block5.targetValues.add(1);
				block5.blockNum = 5;
				block5.logDragData = true;
				block5.showTargetFeedback = false;
				block5.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block5.Run();
				break;				
			case 18:
				ClickPage.Run(Instructions.Get(7), "Next");
				break;
			case 19:
				IOtask2Block block6 = new IOtask2Block();
				
				block6.nTargets = 6;
				block6.totalCircles = 15;
				block6.standard24blockprac = true;
				block6.blockNum = 6;
				block6.logDragData = true;
				block6.showTargetFeedback = false;
				block6.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block6.Run();
				break;	
			case 20:
				ClickPage.Run(Instructions.Get(8), "Next");
				break;	
			case 21:
				ProgressBar.Initialise();
				ProgressBar.Show();
				ProgressBar.SetProgress(0, 32);
				
				IOtask2Block block7 = new IOtask2Block();
				
				block7.nTargets = 6;
				block7.totalCircles = 15;
				block7.standard16block = true;
				block7.updateProgressText = true;
				block7.updateProgress = true;
				block7.countdownTimer = true;
				block7.blockNum = 7;
				block7.logDragData = true;
				block7.showTargetFeedback = false;
				block7.rePosition = Counterbalance.getFactorLevel("reliabilityOrder");
				
				block7.Run();
				break;
			case 22:
				ProgressBar.Hide();
				Slider.Run(Instructions.Get(81), "0%", "100%");
				break;
			case 23:
				PHP.logData("postdictionInt1", ""+Slider.getSliderValue(), true);
				break;
			case 24:
				Slider.Run(Instructions.Get(82), "0%", "100%");
				break;
			case 25:
				PHP.logData("postdictionExt1", ""+Slider.getSliderValue(), true);
				break;
			case 26:
				ClickPage.Run(Instructions.Get(9), "Next");
				break;
			case 27:
				IOtask2Block block8 = new IOtask2Block();
				
				block8.nTargets = 6;
				block8.totalCircles = 15;
				block8.offloadCondition = Names.REMINDERS_NOTALLOWED;
				block8.blockNum = 8;
				block8.logDragData = true;
				block8.showTargetFeedback = true;
				block8.totalPoints = IOtask2BlockContext.getTotalPoints(); //carry over points from previous block
				//flip the reliability order
				block8.rePosition = (int) Math.pow(Counterbalance.getFactorLevel("reliabilityOrder")-1, 2);
				
				block8.Run();
				break;	
			case 28:
				Slider.Run(Instructions.Get(10), "0%", "100%");
				break;
			case 29:
				PHP.logData("predictionInt2",  ""+Slider.getSliderValue(), true);
				break;
			case 30:
				ClickPage.Run(Instructions.Get(11), "Next");
				break;
			case 31:
				IOtask2Block block9 = new IOtask2Block();
				
				block9.nTargets = 6;
				block9.totalCircles = 15;
				block9.offloadCondition = Names.REMINDERS_MANDATORY_TARGETONLY;
				block9.blockNum = 9;
				block9.logDragData = true;
				block9.showTargetFeedback = true;
				block9.totalPoints = IOtask2BlockContext.getTotalPoints(); //carry over points from previous block
				block9.rePosition = (int) Math.pow(Counterbalance.getFactorLevel("reliabilityOrder")-1, 2);
				
				block9.Run();
				break;
			case 32:
				Slider.Run(Instructions.Get(12), "0%", "100%");
				break;
			case 33:
				PHP.logData("predictionExt2",  ""+Slider.getSliderValue(), true);
				break;
			case 34:
				ClickPage.Run(Instructions.Get(13), "Next");
				break;
			case 35:
				ProgressBar.Show();
				ProgressBar.SetProgress(16, 32);
				IOtask2Block block10 = new IOtask2Block();
				
				block10.nTargets = 6;
				block10.totalCircles = 15;
				block10.standard16block = true;
				block10.updateProgressText = true;
				block10.updateProgress = true;
				block10.countdownTimer = true;
				block10.blockNum = 10;
				block10.logDragData = true;
				block10.showTargetFeedback = false;
				block10.totalPoints = IOtask2BlockContext.getTotalPoints(); //carry over points from previous block
				block10.rePosition = (int) Math.pow(Counterbalance.getFactorLevel("reliabilityOrder")-1, 2);
				
				block10.Run();
				break;
			case 36:
				ProgressBar.Hide();
				Slider.Run(Instructions.Get(14), "0%", "100%");
				break;
			case 37:
				PHP.logData("postdictionInt2", ""+Slider.getSliderValue(), true);
				break;
			case 38:
				Slider.Run(Instructions.Get(15), "0%", "100%");
				break;
			case 39:
				PHP.logData("postdictionExt2", ""+Slider.getSliderValue(), true);
				break;
			case 40:
				String data2 = TimeStamp.Now() + ",";
				data2 = data2 + SessionInfo.participantID + ",";
				data2 = data2 + Counterbalance.getFactorLevel("counterbtargetvals") + ",";
				data2 = data2 + Counterbalance.getFactorLevel("reliabilityOrder") + ",";
				data2 = data2 + SessionInfo.gender + ",";
				data2 = data2 + SessionInfo.age;
				
				PHP.UpdateStatus("finished");
				PHP.logData("finish", data2, true);
				break;
			case 41:
				ClickPage.Run("The end", "nobutton");
				break;
			
			}
			break;

		/********************************************/
		/* no need to edit the code below this line */
		/********************************************/

		case 1: // initialisation loop
			switch (sequencePosition.get(1)) {
			case 1:
				// initialise experiment settings
				Initialise.Run();
				break;
			case 2:
				// make sure that a participant ID has been registered.
				// If not, the participant may not have accepted the HIT
				CheckIdExists.Run();
				break;
			case 3:
				// check the status of this participant ID.
				// have they already accessed or completed the experiment? if so,
				// we may want to block them, depending on the setting of
				// SessionInfo.eligibility
				PHP.CheckStatus();
				break;
			case 4:
				// check whether this participant ID has been used to access a previous experiment
				PHP.CheckStatusPrevExp();
				break;
			case 5:
				// clear screen, now that initial checks have been done
				RootPanel.get().clear();

				// make sure the browser window is big enough
				CheckScreenSize.Run(SessionInfo.minScreenSize, SessionInfo.minScreenSize);
				break;
			case 6:
				if (SessionInfo.runInfoConsentPages) { 
					InfoSheet.Run(Instructions.InfoText());
				} else {
					SequenceHandler.Next();
				}
				break;
			case 7:
				if (SessionInfo.runInfoConsentPages) { 
					Consent.Run();
				} else {
					SequenceHandler.Next();
				}
				break;
			case 8:
				//record the participant's counterbalancing condition in the status table				
				if (!SessionInfo.resume) {
					PHP.UpdateStatus("" + Counterbalance.getCounterbalancingCell() + ",1,0,0,0,0");
				} else {
					SequenceHandler.Next();
				}
				break;
			case 9:
				SequenceHandler.SetLoop(0, true); // switch to and initialise the main loop
				SequenceHandler.Next(); // start the loop
				break;
			}
			break;
		case 2: // IOtask1 loop
			switch (sequencePosition.get(2)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 2 *
			 * This runs a single trial of IOtask1                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask1Block block = IOtask1BlockContext.getContext();

				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0, false);
				}

				SequenceHandler.Next();
				break;
			case 2:
				// now initialise trial and present instructions
				IOtask1InitialiseTrial.Run();
				break;
			case 3:
				// now run the trial
				IOtask1RunTrial.Run();
				break;
			case 4:
				// we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(2, true);
				SequenceHandler.Next();
				break;
			}
			break;
		case 3: //IOtask2 loop
			switch (sequencePosition.get(3)) {
			/*************************************************************
			 * The code here defines the sequence of events in subloop 3 *
			 * This runs a single trial of IOtask2                       *
			 *************************************************************/
			case 1:
				// first check if the block has ended. If so return control to the main sequence
				// handler
				IOtask2Block block = IOtask2BlockContext.getContext();
				
				if (block.currentTrial == block.nTrials) {
					SequenceHandler.SetLoop(0,  false);
				}
				
				SequenceHandler.Next();
				break;
			case 2:
				IOtask2InitialiseTrial.Run();
				break;
			case 3:
				//present the pre-trial choice if appropriate
				if (IOtask2BlockContext.currentTargetValue() > -1) {
					IOtask2PreTrial.Run();
				} else { //otherwise just skip to the start of the block
					if ((IOtask2BlockContext.getTrialNum() > 0)&&(IOtask2BlockContext.countdownTimer())) {
						//if we're past the first trial and there's a timer, click to begin
						ClickPage.Run("Ready?", "Continue");
					} else {
						SequenceHandler.Next();
					}
				}
				break;
			case 4:
				if (IOtask2BlockContext.getContext().standard24block == true | IOtask2BlockContext.getContext().standard24blockprac == true | IOtask2BlockContext.getContext().standard16block == true) {
					IOtask2ChoiceOverwrite.Run();
				}  else {
					SequenceHandler.Next();
				}
				break;
			case 5:
				if (IOtask2BlockContext.getNTrials() == -1) { //if nTrials has been set to -1, we quit before running
					SequenceHandler.SetLoop(0,  false);
					SequenceHandler.Next();
				} else {
					//otherwise, run the trial
					IOtask2RunTrial.Run();
				}
				break;
			case 6:
				IOtask2PostTrial.Run();
				break;
			case 7:
				//we have reached the end, so we need to restart the loop
				SequenceHandler.SetLoop(3,  true);
				SequenceHandler.Next();
				break;
			}
		}
	}
	
	private static ArrayList<Integer> sequencePosition = new ArrayList<Integer>();
	private static int whichLoop;

	public static void SetLoop(int loop, Boolean init) {
		whichLoop = loop;

		while (whichLoop + 1 > sequencePosition.size()) { // is this a new loop?
			// if so, initialise the position in this loop to zero
			sequencePosition.add(0);
		}

		if (init) { // go the beginning of the sequence if init is true
			sequencePosition.set(whichLoop, 0);
		}
	}
	
	// get current loop
	public static int GetLoop() {
		return (whichLoop);
	}

	// set a new position
	public static void SetPosition(int newPosition) {
		sequencePosition.set(whichLoop, newPosition);
	}

	// get current position
	public static int GetPosition() {
		return (sequencePosition.get(whichLoop));
	}
	
	// get current position from particular loop
	public static int GetPosition(int nLoop) {
		return (sequencePosition.get(nLoop));
	}
}
