The debate game

Napkin with ideas (backlog):

- minimum 2 candidates
- rounds -
- simultaneous choice of action
- async communication and selection of actions
- keep track of the overall intensity of the debate

- introduce some more varied mechanisms for deciding a round (see N.2.1)

- notification of a round being decided
- highlight the rounds that have been decided since the last visit

- timeout = how long can one delay selecting an action

N.2.1 - mechanisms for deciding a round?
- voting and public choice (involves public voting and some kind of voting mechanism)
- based on some kind of “correct” answer?
- based on randomised decision support?
- (a combination of these?)


N.2.2 - the influence of individual rounds on the outcome of the overall debate
- mechanism for deciding the debate - (number of rounds won? Weighted? Public support?)
- the influence of round and overall intensity on the round effect (one candidate may win more or less convincingly)
-


User Stories


As a Candidate, one can get information on the current debate round,
one can see the actions that are available to them,
and select their action for that round (maybe get some feedback on that?).

As a Candidate, I can see the results of rounds that have been decided.

As a Candidate, I can see the current state of a Debate that I am involved in,
information on all the completed rounds.



Requirements

My thoughts and comments:
- A Candidate requests information about a debate round ->
  Based on Candidate information a menu of actions is chosen
  The Rounds component keep track of debate rounds -> updated when a Candidate selects their action
  The outcome of a round is calculated when all candidate have submitted their action.



R1 - Round handling
R1.1 - An ongoing round allows candidates select actions
R1.1.1 - the Resolution component received (via HttpRequest) A CompletedRound object and returns a ResolvedRound?
R1.2 - When all candidates have selected their action - the round is updated with the outcome and set to “resolved”
R1.2.1 - The mechanism for deciding the outcome will be described here! (TODO).



Architecture

DebateRound - some topic, various parameters, Secretly selection actions
CandidateAction - Opinion on the Topic, Intensity

Candidate - the way my system represent the user


-

            Front-end ——>  Gateway

						^
						|
			
					Rounds (Ongoing)

		 (Actions) 			


							Resolution



			Candidate






Resolution component:

- /resolve - POST  - CompletedRound
  ResolvedRound


Rounds component:

- /activeRounds - GET -
- /round/{id} - GET
- /round/{id} - POST


MVP:

- Aiming for: Display rounds, display actions, select actions, show outcome of current round
- Best case scenario: all that plus: debate level information, multiple rounds, debate outcome, more mechanisms to decide rounds
- Minimum (worst) case: - Handling and resolving a round - 

