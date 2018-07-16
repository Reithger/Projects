/*
 * A program for WGST-1001 that explores the concept of gender across several periods in our culture
 * by the conceit of a series of magazine-style points tests that tell you what your gender ultimately is.
 * First movement is you performing the tests and getting results based on the questions.
 * Second movement is to move back through the changing views and comment briefly on all of the ideas therein.
 * Finishes with a statement that gender isn't simple etc.
 * This file works in conjunction with the Mechanics file included alongside it, but it should all export together.
 * 
 * Mac Clevinger
 * Started November 6, 2017
 * WGST-1001
 * Dr.Krista Johnston
 * 
 * Time to start writing in two languages.
 */

import java.awt.*;
import javax.swing.*;

class Presentation {

	public final static String splashStr = "Start";	//First screen that user starts from - label of the Button
	
	public final static String firstPremise = "Have you ever looked in a mirror and asked yourself: Is that really me? Have you ever looked down in the shower and wondered: "
											  + "Is this really me? Have you ever seen someone of the opposite sex and wondered: What if that was me?"
											  + " Well wonder no longer! In a marvelous display of scientific ingenuity, a simple test has been designed that"
											  + " can not only accurately* guess** your true gender***, but do so across four seperate eras to ensure the results"
											  + " are consistent! If you're tired of being tired, if you're tired of spending nights in turmoil over the idea of"
											  + " your gender, then let those worries be put to rest. You can trust us: We did the science****.©"
											  ;	//Idea that you are doing a magazine test using old found files. - Paragraph text
	public final static String feministPremise = "While the idea that gender is something you can take a ten question quiz to figure out conclusively is nice,"
												 +" the matter is considerably more complicated than that. As was quite clear, each era had shifting ideas of gender,"
												 +" not because the people changed but because the society and what it needed from its people did. To the individual,"
												 +" gender is an identity, but to society and those who control it, it's a tool to police behaviour and subtly influence"
												 +" how we think and act. So let's take another look at those four eras of society, and ask ourselves: why were things like"
												 +" this, who benefits because people were expected to behave in this way, and why did it change?"
												  ;	//Idea that you are reviewing the fallacies in all of these tests. - Paragraph text
	
	public final static String[] titles = new String[]{"Ready to Discover The Truth About Yourself?", "Let's Get This Started!", "And there we have it."};	//Holds titles of each page for checking when it has switched. - Screen header
	public final static String[] loadingBars = new String[]{"teen_cosmo_2014_gender_quiz.pdf",
															"teen_cosmo_1967_gender_quiz.pdf",
															"the_metropolitan_book_of_etiquette_1832_edition.pdf",
															"decorums_a_practical_treatsie_on_etiquette.pdf",
															"decades_of_feminist_literature.pdf"};	//Text to show during loading screens (what genre you're in).

	public final static double[] waitTimes = new double[]{5,3,3,3,3};	//Edit back to normal values
	
	public final static String[] questionSet1 = new String[]{"Q1: You've been taken to the toy store! What toy do you get?",
															 "Q2: While at the mall, you see an outfit that you love. What is it?",
															 "Q3: It's field day at school! What sport do you choose to play?",
															 "Q4: Your birthday is coming up! What kind of party do you throw?",
															 "Q5: For a few weeks you've been nursing a crush on someone; what do you do?",
															 "Q6: Your friend comes to you in tears about a terrible incident they suffered. You:",
															 "Q7: After a meeting at work, you notice that the room was left in a mess!",
															 "Q8: While walking home, a passerby starts screaming at you and getting in your face.",
															 "Q9: Your friend tells you that they're a Feminist; how do you react?",
															 "Q10: You're cooking at home, and cannot for the life of you open a jar of pickles."};	//Batch of 10 questions for first Set. - Sentence text
	public final static String[] questionSet2 = new String[]{"Q1: It's Christmas Eve and, as you lie in bed, you dream of what gift?",
			 												 "Q2: You wake up for Sunday Service and look in your wardrobe; what do you wear?",
			 												 "Q3: Your curfew's at nine, but there's a party at eleven you want to go to. What do you do?",
			 												 "Q4: You're at the movies, sitting beside someone you have a crush on. How do you get them to notice you?",
			 												 "Q5: You stayed at work later than usual, and have to walk home in the dark. What do you do?",
			 												 "Q6: Every Thursday night you and friends meet at the local Recreation Center for what purpose?",
			 												 "Q7: You've been laid off, and are looking for a new job; what interests you?",
			 												 "Q8: A flyer for a local STEM college hits you in the face; how do you react?",
			 												 "Q9: You see a story in the newspaper about a Feminist rally in your town; how do you feel?",
			 												 "Q10: You've gotten married! Acquired children! Bought a home! What role do you have in that home?"};; //Batch of 10 questions for second Set. - Sentence text
	public final static String[] questionSet3 = new String[]{"Q1: If one were to request where you are at most comfort, you would reply:",	//Tonally, it's a headmistress expecting a specific answer despite asking
			 												 "Q2: To what height of importance do you hold your most esteemed family?",
			 												 "Q3: Suppose a friend has shown a viper's tongue, and seeks forgiveness when confronted. How do you proceed?",
			 												 "Q4: When courting and wishing to show your finest trait, what do you present?",
			 												 "Q5: At a closed gala, a potential courtier makes themselves known to you. What is the proper course of action?",
			 												 "Q6: You've enjoyed regular work for some time now; to what or whom does this pleasant sum go?",
			 												 "Q7: A messenger has delivered startling news to your homestead, assaulting your very senses. How do you react?",
			 												 "Q8: You have been wedded in the holiest of unions, and may now enjoy what manner of life?",
			 												 "Q9: A villanious cabal has been hounding you at your work for some time now. What is the appropriate response?",
			 												 "Q10: What brings joy to your heart?"};; //Batch of 10 questions for third Set. - Sentence text
	public final static String[] questionSet4 = new String[]{"Q1: As a child, did you have the honour of a Breeching Ceremony?",
			 												 "Q2: With what gifts has nature endowed your vessel?",
			 												 "Q3: Suppose a foul illness has taken root in your home; what do you do?",
			 												 "Q4: Your spouse wishes to share your bed with another, and asks that you prepare it. What do you do?",
			 												 "Q5: At what age did you or do you expect to be wedded?",
			 												 "Q6: If widowed, how long should one wait before being wed again?",
			 												 "Q7: How much education have you enjoyed in your life?",
			 												 "Q8: You have been caught committing the crime of theft; what is your punishment?",
			 												 "Q9: Do you assist your spouse in their work? If so, how much?",
			 												 "Q10: You hear a rumour in the market that you are sexually promiscious. What does this mean to you?"};; //Batch of 10 questions for fourth Set. - Sentence text
	public final static String[][] questionSets = new String[][]{questionSet1, questionSet2, questionSet3, questionSet4};	//Collection for convenience.
	
	public final static String[] answerSet1 = new String[]{"A beautiful doll","A set of paints","A new baseball bat","A telescope",
														   "A gorgeous dress and shoes to match","A punky skirt and matching black T-Shirt","A pair of baggy jeans and a sweatshirt","A snazzy suit",
														   "Tennis!","Track & Field!","Baseball!","Javelin!",
														   "Wine, pizza, and romantic comedies","A slumber party full of gossip","Beer and Pizza at the bowling alley","A kegger at Mike's house",
														   "Pointedly ignore them until they notice","Get a friend to talk to them for you","Ask them out to dinner","Surprise them with flowers",
														   "Listen sympathetically and comfort them","Tell them about the terrible day you've had","Tell them to quit complaining","Tell them to calm down, they're being hysterical",
														   "I should clean this place up!","The boss'll be angry if it gets left like this...","Why hasn't someone cleaned this?","I should try to be cleaner in the future.",
														   "I run away screaming","Duck my head and keep moving","Punch them right in their face","Stare them down",
														   "Give them the secret handshake","Tell them how you don't need Feminism","Warn them of the dangers of Feminism","And?",
														   "Get someone to help you out","Run it under a warm faucet","Focus your inner power and pop it open","Give up on pickles forever"}; //Batch of 40 answers for first Set. - Label text
	public final static String[] answerSet2 = new String[]{"So much chocolate","An Easy-Bake Oven","Hot Wheels... with the fire decal","A G.I. Joe",
			   											   "A plain dress in a pretty pink","Black eyeliner, black skirt, black leggings, black T-shirt, and occult jewelry","A respectful suit and tie","Whatever's on the floor that doesn't make you gag",
			   											   "Respect your parent's wishes and stay home","Tell them you're going to study at a friend's house","Talk to them; you're not a child anymore","Sneak out through the window",
			   											   "Wait for them to make the first move","Slowly reach for their hand","Pretend to yawn and drape your arm around them","Get your friend to harass them so you can defend them and win their heart",
			   											   "Stay in the streetlights and hold your keys","Call a friend for a ride","So? I'll just walk home","Keep a cautious eye as you walk",
			   											   "A local gardening club's get-together","PTA Meetings","A few hands of poker and polite drinking","Shooting some alley-oops (the basket balls)",
			   											   "An ad for professional typists","A help-wanted sign for a waitress","A garage in need of a mechanic","A local construction site with vacancy",
			   											   "Feel a distant longing but know you wouldn't fit in","Keep it; there'd be one more applicant this year","Realize it's for the school you already attend","Ignore it; you already have a job",
			   											   "Inspired to take part","Annoyed that they're going to clog the streets","Proud of their determination","Worried for the future of these poor, confused people",
			   											   "Taking care of the family","Managing the family's finances","Working to support the family","Acting as the head of the household"}; //Batch of 40 answers for second Set. - Label text
	public final static String[] answerSet3 = new String[]{"At home, managing the daily activities, of course","Perhaps on a walk about the garden; briefly, of course","With my feet up before the hearth","In the midst of my work",
			   											   "Second to none, for they are my heart","As dear relatives whom I visit often","At a distance with a respect for their efforts","As great beneficiaries to my life",
			   											   "Forgive them at that very instant and speak no more","Let them know they are forgiven, and remind them often","Demand that they retract their statement to all that heard","Forsake the viper and devil they truly are",
			   											   "A flashing, yet modest, face","My nurturing and kind nature","A strength to protect any deserving of it","My supreme intelligence that can solve any of their problems",
			   											   "Approach them, yet act as they had approached you","Ignore them in such a way they know you don't care","Muscle past your rivals and kiss their hand","Catch their eye and attract them to a private tete-a-tete",
			   											   "To my family, specifically my parents","To my family, specifically my spouse","Enough to my family to cover expenses and the rest for pocket money","What money makes it home may stay there",
			   											   "I faint upon the spot","I summon the attentions of the family with my tears","I maintain a stony disposition","In anger I depart to set matters right",
			   											   "A comfortable stay at home, awaiting my spouse's return","The pain of separation as I leave the home for my work","A life filled with joy and light as my household is well-kept","A life now tempered by the gentility I lacked",
			   											   "Say nothing, do nothing, and await God's wrath","Speak softly to a friend for relief, but do no more","Hold those scoundrels accountable in one form or another","Inform a manager of this startling behavior",
			   											   "Serving your spouse and household","Being a warm and gentle force in a harsh world","Serving your race as a protector and upholder of peace","Sex"}; //Batch of 40 answers for third Set. - Label text
	public final static String[] answerSet4 = new String[]{"No; such an honour is not mine to have","Not personally, but I witnessed one's majesty","Yes, and my life was irreparably changed afterwards","Yes, but I miss being able to wear skirts",
			   											   "A weaker, yet more inquisitive mind","A more industrious but weaker body","A sharp intellect to rival any other","The strength to move mountains",
			   											   "Prepare a ritual and some salve to remedy the problem","Take care of those afflicted as best I can","Call for the aid of a local healer","Call for the aid of a doctor",
			   											   "Ready the bed at once and await further orders","Prepare the bed, and an herbal tea to ensure the night is a celibate one","Deny their request and punish their insolence","Deny the request; they can make their own bed",
			   											   "13","I'm afraid I have grown too old to enjoy such a pleasure","18","Whenever I spot one I like the look of",
			   											   "Oh, I could never wed again after losing my love","Perhaps after some years of despair if the right person came along","A few weeks, perhaps a month to mourn","However long it takes to find a new one",
			   											   "Enough to tend the house","I've been fortunate enough to enjoy some, few, academic teachings","Many years, enough to establish myself quite comfortably in the world","To learn is my destiny, to teach: a gift",
			   											   "To be seen in tears publicly; it was but a little thing","Banishment from the land; I committed a grave crime","A steep fine and a thrashing; it was but a little thing","Execution; I committed a grave crime",
			   											   "Every hour of every day is dedicated to their cause","When there is a need and I've the opportunity, of course","Their work is their own, as is mine","No",
			   											   "Perhaps I should keep a cleaner home...","Perhaps I should be more discrete in future...","Conflicted, for I am wed and honourable","Perhaps I should have less sex..."}; //Batch of 40 answers for fourth Set. - Label text
	public final static String[][] answerSets = new String[][]{answerSet1, answerSet2, answerSet3, answerSet4};	//Collection for convenience.

	public final static String[] result1 = new String[]{"A good, strong lad like yourself should go far in life.",
														"A steady head and good heart like yours should get you pretty far."};	//Further flavor text for FinalResult in genre 1
	public final static String[] result2 = new String[]{"Ah, it's good to see a stalwart patriarch such as yourself.",
														"A bit of affirmation that you're on the right track never hurts."};	//Further flavor text for FinalResult in genre 2
	public final static String[] result3 = new String[]{"Your results show you have the makings for a very bright future indeed.",
														"Take care not to strain yourself, my dear."};	//Further flavor text for FinalResult in genre 3
	public final static String[] result4 = new String[]{"You fit the very image of the sporting gentlemen; well done, sir.",
														"You... you know how to read? Did someone help you?"};	//Further flavor text for FinalResult in genre 4
	public final static String[][] resultDescriptions = new String[][]{result1, result2, result3, result4};	//First is Male Description for the genre, Second is Female Description for the genre
	
	public final static String[] genderResult = new String[]{"Male", "Female", ""}; //Thank the lord for vague numbering systems.
	
	public final static String[] commentAnsSet1 = new String[]{"Female: Soft, pretty toys are associated with femininity","Female: Utilitarian gifts that emphasize the arts are associated with femininity","Male: Sports are considered a predominantly male pastime","Male: Utilitarian gifts that emphasize science are associated with masculininty", 
															   "Female: Flowing garments, such as a dress, are considered feminine","Female: Even in counterculture, skirts and the like are seen as feminine","Male: A characteristic of Masculinity is not caring what people think about you, done so by making sure they know someone cares so that they can not care","Male: Suits are a common formal, Masculine, appearance",
															   "Female: Non-contact sports engaged with dexterity are considered Feminine","Female: Endurance sports at an amateur level dealing in dexterity are considered Feminine","Male: A sport about holding a big stick and hitting things while fleeing pursuit; considered Masculine","Male: Simulation of a violent act once used commonly by soldiers to wage war; considered Masculine",
															   "Female: Feminine entertainment is considered to be emotional and classy","Female: The emotional spreading of rumours is considered beneath Men, and therefore Feminine","Male: Gluttonous fulfillment and a pasttime obsessed about big balls is tres Masculine","Male: The generation of a volatile setting wherein cognition is impaired being considered a pleasurable time is tres Masculine",
															   "Female: Active passivity is a trait supposedly honed by Women","Female: Social finagling to the personal benefit is a trait supposedly honed by Women","Male: Active, confident romantic approach is typically a Male activity","Male: Sometimes a guy can be romantic, what's the fuss",
															   "Female: Awareness and compassion for the emotional states of those around them are feminine traits","Female: Women are considered to be more willing to open up emotionally to one another","Male: A no-nonsense approach that doesn't allow for emotional clarity is the Male American Way","Male: Because telling someone to calm down when they're freaked out is so helpful, Greg",
															   "Female: Women are expected to carry the load at work and do housekeeping for the Men","Female: Being aware of social gendered expectations seems to be a predominately feminine trait","Male: Ignorance of social gendered expectations seems to be a predominately masculine trait","Male: Causing problems that you can expect others to pick up after you for is a trait Men can get a pass on",
															   "Female: Street harassment is a dangerous prospect for Women in a culture that doesn't take the matter seriously","Female: While older expectations of passivity are less prominent, Women are still expected to turn the other cheek and not react","Male: A hyper-violent response to being threatened, while not a proper legal response, is a socially cultivated one for Men","Male: Assertions of dominance are a requirement if Men are to prove themselves to be the 'Alpha'",
															   "Female: Feminism has grown and changed since its popularization in the 1900's, entering the mainstream to positive and negative results","Female: There is a route of thinking born from late 1900's Feminism that, once their social group of Women got what they wanted, the entire movement was over. It was not","Male: Feminism poses a threat to the partriarchy and male-driven social order, so there's an interest in not promoting the movement","Male: While the abolishment of patriarchy would bring benefits to Men in their newfound freedoms, it isn't as essential to them as it is to Women",
															   "Female: Eating one's pride so as to eat one's pickle is an accepted behavior for Women","Female: A touch of cleverness in solving a problem is a trait given more to Women than to Men","Male: The pride-filled desire to do it yourself and not need help is a behaviour implanted into many Men","Male: The pride-filled desire for Men to ensure no one knows that they have 'failed' a task is a cult classic",};	//Comments on each answer of the first Set. - Sentence text - AlteredSingleQuestionScreen
	public final static String[] commentAnsSet2 = new String[]{"Female: While a tasty treat for all, it's considered to be bonded closely to Women","Female: Women must have an intrinsic interest in cooking, right? Let's make sure it happens","Male: 'Cool' things like fast-action cars and dangerous fire are tied into Masculinity","Male: Educating children to revere the military and make sure Boys want to trek around other countries",
			   												   "Female: Warm colors such as pink and flowing garments like dresses are inexplicably feminine","Female: Counterculture tries to break away from norms, but some are deeply embedded","Male: The suit and tie are seen as intrinsically masculine, but look lovely on anyone","Male: A lack of personal cleanliness is an expectation of masculine youth and a failure in feminine youth",
			   												   "Female: Dutiful, good behavior is the epitome of young womenhood","Female: Solving a problem by craftiness while engaging in disallowed behaviour is the epitome of young womenhood","Male: Responsibly facing a problem head on with no double-talk or confusion is the epitome of the male youth","Male: Avoiding conflict while behaving in a way to generate conflict is the epitome of the male youth",
			   												   "Female: Modesty and passivity are the watchwords of this era's behavioral norms","Female: Working past the passivity of prior cultures, Women start practicing agency","Male: This image is imprinted in the social consciousness, c'mon","Male: The thing that Romantic Comedies teach young men to do to definitely win a girl's heart and not harass them",
			   												   "Female: The night is dangerous for Women in a culture that encourages Male-boldness and Female-passivity","Female: Great pains can be taken for a person to avoid danger directed at themselves","Male: While threats exist, it is much reduced for Men who exist in a safer demographic than Women","Male: Danger exists at night, but Men are statistically safer than Women at night",
			   												   "Female: Nurturing living things is a pasttime associated with femininity","Female: Caring about children and their future is associated with femininity","Male: Social engagement oriented around desire fulfillment and an assertion of skill among their peers; masculine","Male: Sports as a past-time were typically consigned to masculine activities",
															   "Female: Menial, repetitive tasks were considered beneath Men until Computers became a Science","Female: Work that emulates an older power dynamic of being served by a person required to at least pretend to care and be compassionate falls into the feminine category","Male: Having practical skills in understanding the workings of cars is inexplicably masculine","Male: Menial, physical labour that took place outside of the home was typically relegated to Male workers",
															   "Female: The hard sciences were not welcoming to Women, and still aren't","Female: Despite there being a resistance for Women in science, many still pushed past Male pride to do wonderful things","Male: Most scientists were, and continue to be, for many fields Male","Male: With sufficient privilege, you can ignore some valuable facets of society without issue!",
															   "Female: Some Women acknowledged the issues in society Women face and act to change them","Female: Some Women don't acknowledge the issues in society Women face and are bothered by those who do","Male: Some Men acknowledge the issues in society Women face and at the least don't work antagonistically","Male: Some people are more worried about the change in social dynamics than in the problems that are being changed",
															   "Female: Nuclear family expectations of a Woman is to be a caretaker","Female: Nuclear family expectations of a Woman is to take care of the finances","Male: Nuclear family expectationss of a Man is to be the breadwinner","Male: Nuclear family expectations of a Man is to be patriarch of the family",};	//Comments on each answer of the second Set. - Sentence text
	public final static String[] commentAnsSet3 = new String[]{"Female: At this time, Women were expected to desire a home to keep well maintained","Female: In the era of the Yellow Wallpaper, one musn't become too excited","Male: The dream was to have a fulfilling job and a comfortable seat to exultate in at the end of the day","Male: Men were free to have desires and lifelong goals that didn't fit into a stereotype",
			   												   "Female: Young Women were expected to be dutiful daughters that minded their parents","Female: After leaving home, Women should still fit into the dutiful daughter narrative","Male: Men were expected to go out from home and make their own way in the world","Male: Because Men could leave home and had agency, this permitted complicated relationships",
			   												   "Female: Women were to be the Angel of Mercy at every turn","Female: While Women should be merciful, there was still a social pecking order to act in","Male: To be the arm of Judgement is the duty of Man","Male: An Angel of Mercy Man is not, but of Judgement and Punishment",
			   												   "Female: One of Women's best features was thought to be their appearance","Female: One of Women's best features was thought to be their kind disposition","Male: Society wants strong lads around for when a war is brewing","Male: Intelligence is a desirable trait... in Men, at this time. The Women were too scary",
			   												   "Female: A woman was to act passive and let others be the agents of change","Female: While meant to be passive, a woman could still be actively passive","Male: Men were active in courtship, competing with rivals in some circumstances so as to decide for the Women who to choose","Male: Men had to be active, but could do so with tact and perhaps even some romance",
			   												   "Female: When unmarried, a Woman's funds were to belong to the family","Female: When married, a Woman's funds were to go to the Husband","Male: Men were free to be in control of their own wealth and income","Male: In being free to control their wealth, Men can spend it rashly and poorly",
			   												   "Female: Women were considered frail and weak in the face of adversity","Female: When a Woman is confronted, a white knight gets his armour","Male: Emotion is not permitted in the house of Man","Male: In the face of adversity, Man must strike back",
			   												   "Female: Women were expected to stay at home whenever possible and serve their husband","Female: If a Women retained work, it wasn't considered a good thing and must reflect her own moral struggles","Male: While marriage brought a Woman work, it was expected to bring a Man physical and emotional relief","Male: The term 'better half' applies here",
			   												   "Female: An agent of action a Woman is not meant to be","Female: Emotional relief and healthy communication are Women's property","Male: Man is active, and punches his problems in the face","Male: Perhaps not the most direct, but utilizes the secondary features of the body to think about solutions",
			   												   "Female: Convincing most Women to want to be in a position of service to Men is a great party trick","Female: Women are to be a font of goodwill and kindness in a terrible world","Male: Hero complexes are good for society, right? Right?","Male: Just breaking the mood a little, y'know?",};	//Comments on each answer of the third Set. - Sentence text
	public final static String[] commentAnsSet4 = new String[]{"Female: The Breeching Ceremony was an event wherein a young boy was given his first pair of breeches, or trousers","Female: The Breeching Ceremony was a momentous occasion in families, one held only for boys","Male: The Breeching Ceremony was a coming of age event for boys, whereafter they were no longer to be coddled","Male: Before the Breeching Ceremony, all children wore skirts, but after boys were expected to wear only their breeches",
			   												   "Female: The differences in intellect between Men and Women was thought to be Power vs. Finesse respectively","Female: The differences in strength between Men and Women were thought to be Power vs. Determination respectively","Male: Men were just allowed to be good at a thing without there being a big deal","Male: Men were just allowed to be good at a thing without there being a big deal",
			   												   "Female: Women were thought to have a close bond to healing and the occult","Female: Women were expected to be caretakers in the home","Male: There was a healthy respect for local women skilled at the medical arts","Male: When Men learned how to heal, they discredited traditionally female healers and took their place",
			   												   "Female: Women were expected to obediently serve their husbands without question or hesitation","Female: Women do possess agency even if commonly thought to be obedient slaves","Male: It goes without saying that a Husband would not be happy in this instance, nor should a Woman but them's the times","Male: I like to think they didn't listen past being asked to make the bed",
			   												   "Female: Like... Romeo and Juliet, y'know?","Female: If a Women wasn't wed early enough (while they were 'pretty'), it was pretty much over for them","Male: The ideal Romeo and Juliet scenario which is still dodgy as hell","Male: The realistic Romeo and Juliet scenario",
			   												   "Female: Women typically did not get remarried after their spouse died","Female: Without a monetary motivation, an older widowed woman was not looked upon with interest","Male: Men could rebound very quickly after being widowed, and were expected to","Male: Impassionate marriages were totally a thing",
			   												   "Female: Usually, women were educated only in practical matters of housekeeping","Female: Rarely, a woman may receive academic teachings and not simply practical","Male: Education as we know it is what Men got; school, university, actual knowledge","Male: Positions of educational authority belonged to Men",
			   												   "Female: Courts issued reduced punishments to Women in most cases","Female: Unless a Women worked in cahoots with a Man, she suffered reduced penalties","Male: The typical punishment for crimes was a fine and perhaps a lashing","Male: In extreme cases, the Courts could be quite severe in their punishment",
			   												   "Female: Women in marriage were housekeepers, but also aided their Husbands in many endeavours","Female: Women had their duties to perform, but often assisted their husbands without being formally accredited","Male: While Women were expected to help their Husbands, Men did not typically reciprocate, a fact acknowledged by a bare few","Male: Yeah, no",
			   												   "Female: During this time, the cleanliness of the home was proportional to a woman's virtuousness","Female: A sexually promiscuous women was not looked on positively at this time","Male: Sometimes gossip gets around, what can you do?","Male: Not as big a deal for a Man as opposed to a Woman, but it really doesn't carry the connotations about home cleanliness",};	//Comments on each answer of the fourth Set. - Sentence text
	public final static String[][] commentAnsSets = new String[][]{commentAnsSet1, commentAnsSet2, commentAnsSet3, commentAnsSet4};	//Collection for convenience.
	
	public final static String[] wrongSet1 = {"Today there is a growing awareness of the patriarchy in Male behaviour, which many Men have begun to see in themselves."
											  +" This has led to a cultural conflict raging between two modes of thought: That the past systems of gender in society which exploited"
											  +" Women as being beneath Men were mistakes not to be repeated, and that those systems are the natural order and should be"
											  +" reconstituted. Part and parcel with this conflict is the adherence to the model Male that society pushes: emotionally"
											  +" closed, indomitable, strong, succesful, and deserving of the attention of Women. Many of the people, predominately Male, that"
											  +" have control of sweeping measures to institute either side of this conflict fall into the latter category, but awareness is spreading.",
											  "On the surface, it appears that equality has become the norm between Men and Women, but an examination of social"
											  +" undercurrents tells a different story. Women who go 'above their station' face push-back from industries entrenched in"
											  +" divisive perceptions of gender, and a lot of effort is put into maintaining control over what a Woman can do."
											  +" The idea that Women exist for Men permeates the media which, alongside messages about how to look, act, and think,,"
											  +" bombards the youth, teaching them norms that enforce the very concepts which Women have now spent over a century"
											  +" fighting to dissolve in the hopes of attaining legal and social equality. Much has changed for the better, but the fight continues."};	//Final bit of commentary on the genre of first Set looking at Male/Female/Overall. - Paragraph text	- AlteredFinalResult
	public final static String[] wrongSet2 = {"In the time between the Industrial Revolution and the late 1900's, society changed a lot. Gender was now a barrier"
											  +" between opportunities, not a locked door, which threatened Male superiority. The culture became obsessed with maintaining"
											  +" a dynamic that was proven wrong every time a Woman succeeded in a place that 'belonged' to Men, so it tried to"
											  +" strike back. The Noble Man, protector of the land, was told that these changes to Women were going to hurt her, hurt all of"
											  +" society, and keeping them in their place was the only choice, so Men as a collective drove Women away from 'Male' spaces"
											  +" and tried to revert to a time when Husbands owned their Wives, the objectification of the Female meant to devolve them.",
											  "The development of Feminism and its corresponding literature led to sweeping social movements to empower Women and"
											  +" deal with the oppression that society placed on them. Through the incidence of being useful for the generation of capital,"
											  +" they were no longer objects but a demographic of society to appease as independence let them mobilize and give voice to the"
											  +" injustice they had faced. Women had power they now exercised in direct conflict with the roles a Male-driven"
											  +" society wanted them to act in, and they were aware that this system existed. It was perpetuated by the oppression they suffered"
											  +" and it's goal was to convince everyone that Women were supplemental to Men and to be controlled, so they fought back."};	//Final bit of commentary on the genre of second Set looking at Male/Female/Overall. - Paragraph text
	public final static String[] wrongSet3 = {"With the birth of automation and the ensuing sweeping changes to society, it was hard in some respects for the old"
											  +" norms of gendered behaviour to keep up; factory owners realized that the machine didn't care if the hands were male"
											  +" or female, and set both to work in the steady rise of their capitalistic empires. This changed the paradigm that Men"
											  +" had worked within for centuries; Women's work was no longer just home and child care, but jobs they had a predisposition"
											  +" for based on social conditioning. On top of that, Women had a private income that would become steadily less controlled"
											  +" by their families and Husbands; society would have to begin to respect Women, changing that power dynamic.",
											  "During the Industrial Revolution, a new avenue opened up for many Women in society: factory work. While still "
											  + "indebted to their household, be it parental or spousal, they now had a private income and a growing normality about "
											  +" their working. Behavioral expectations still lay towards passivity and kind-hearted prettiness, but "
											  +" the wheels started turning towards independence now that there was a path to living outside of the parental"
											  +" home that didn't involve heterosexual marriage. This lay the groundwork for Women to seriously question"
											  +" the place in society they had historically held, and do something to change that."};	//Final bit of commentary on the genre of third Set looking at Male/Female/Overall. - Paragraph text
	public final static String[] wrongSet4 = {"At this time, the Renaissance, Men were people and Women were worse-people. If you were a Man, you could"
											  +" get an education, own your work, go out and about for pleasure and the fulfillment of personal interests,"
											  +" so congratulations if that's what you got! If you ever got wed, you could look forward to a Wife socially"
											  +" bound to maintaining your household and being an obedient slave who would do all of her work and any of yours"
											  +" that needed doing. While being the only gender that was taken seriously by society had a drawback in legal"
											  +" proceedings, it was a benefit basically everywhere else."
											  ,
											  "During the Renaissance, Women were to be passive, untoward, and industrious workers who existed as a supplement"
											  +" to Men. Education was poor and centered around housecare, the rare exception being considered a Woman overcoming the circumstances"
											  +" of her birth and setting a poor example to her sisters. For a time Women garnered respect as healers and associates to the occult"
											  +" so as to aid the sick and hurt, but then they were discredited by Male doctors who didn't allow Women to attend their schools"
											  +" because they were Women, and used that lack of knowledge as proof they weren't smart enough to attend. In marriage a Woman"
											  +" toiled in both her and Her husband's work, not that Men credited them despite it being very common."};	//Final bit of commentary on the genre of fourth Set looking at Male/Female/Overall. - Paragraph text
	public final static String[][] wrongSets = new String[][]{wrongSet1, wrongSet2, wrongSet3, wrongSet4};	//Collection for convenience.
	
	public final static String finalStatements = "Besides being a retrospective on the history and origins of Gender Norms in a predominately Western Society,"
												 +" this look-back also points out an interesting property of Gender: It's a construct. It is a tool of society"
												 +" to alter the behaviours of its members to benefit either the collective or the dominant group often at the cost"
												 +" of one group's equality. It's arbitrary, and has no inherent meaning until engaged with the norms a specific society"
												 +" possesses. 'Man' and 'Woman' alone are nothing; they have meaning because we assume 'Man in my current society', but 'Man in the 1800's' takes on a very different meaning from 'Man in 2013'."
												 +" The differences in treatment and consideration, then, aren't intrinsic to being 'Man' or 'Woman', but how that culture has"
												 +" constructed itself to treat the two, so it's pointless to ask why 'Man' and 'Woman' are different; instead ask why you're"
												 +" being expected to differentiate the two, considering how the existence of intersex and transgender people destroy that binary,"
												 +" and how very hard this society tries to erase and hide the people that complicate the business of creating difference.";	//Paragraph text for FinalFinalResult; end thoughts on matter of gender norms across societies.
	
	public static void main(String[] args) throws AWTException{
		JFrame screen = Mechanics.Splash(splashStr, titles[0], titles[1]);	//Opening Screen
			screen.setVisible(true);
				while(screen.getTitle().equals(titles[0])){System.out.print("");}
		Mechanics.IntroPremise(screen, firstPremise, loadingBars[0]);		//Screen 2 - 'This is a magazine type test'
			screen.setVisible(true);
				while(screen.getTitle().equals(titles[1])){System.out.print("");}
				
		for(int i = 0; i < questionSets.length; i++){		//Goes through genres forwards (present to past);
		  Mechanics.Loading(screen, loadingBars[i], waitTimes[i+1]);	//Faux-loading screen that introduces the next genre of test.
		  int testResult = Mechanics.dealWithResult(Mechanics.QuestionnaireOverall(screen, questionSets[i], answerSets[i]));	//This calls all the actual test screens
		  Mechanics.FinalResult(screen, genderResult[testResult], resultDescriptions[i][testResult]);			//This displays the result of a single test, transitions into next genre.
		  String baseTitle = screen.getTitle();
		  screen.setVisible(true);
		  while(screen.getTitle().equals(baseTitle)){System.out.print("");};
		}
		Mechanics.Loading(screen, loadingBars[loadingBars.length-1], waitTimes[0]);	//Faux-loading screen introducing idea of review of previous test material.

		
		Mechanics.IntroPremise(screen, feministPremise, loadingBars[loadingBars.length-2]);	//Further introduction that going to review material w/ feminist thought.
		  screen.setVisible(true);
		    while(screen.getTitle().equals(loadingBars[loadingBars.length-1])){System.out.print("");};

		for(int i = questionSets.length-1; i >= 0; i--){	//Goes through genres backwards (past to present)
			Mechanics.Loading(screen, loadingBars[i], waitTimes[i]);	//Faux-loading screen, much faster, reminds user what they're looking at.
			Mechanics.AlteredQuestionnaireOverall(screen, questionSets[i], answerSets[i], commentAnsSets[i]);	//Actual call to each questionnaire w/ commentary
			String baseTitle;
			for(int j = 0; j < 2; j++){
			  baseTitle = screen.getTitle();
			  Mechanics.AlteredFinalResult(screen, genderResult[j], wrongSets[i][j]);		//Explains significance of each result and overall commentary.
			  while(screen.getTitle().equals(baseTitle)){System.out.print("");};
			}
			screen.setVisible(true);
		}
		
		screen.setTitle(titles[2]);
		Mechanics.FinalFinalResult(screen, finalStatements);		//Final screen to discuss change of gender norms/why they are the way they are based on social need.
		while(screen.getTitle().equals(titles[2])){System.out.print("");};
		System.exit(0);												//Ends program when continues from here.
	}
}
