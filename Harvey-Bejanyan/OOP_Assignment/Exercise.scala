

class Exercise(name: String, exerciseType: String) {
  private var weightsUsed = 0
  private var repetitions = 0
  private var sets = 0
  private var distance = 0

  def setWeightsUsed(weights: Int) {weightsUsed = weights}
  def setReps(reps: Int) {repetitions = reps}
  def setSets(sets: Int) {this.sets = sets}
  def setDistance(distance: Int) {this.distance = distance}

  def getName():String = {return name}
  def getType():String = {return exerciseType}
  def getWeightsUsed():Int = {return weightsUsed}
  def getReps():Int = {return repetitions}
  def getSets():Int = {return sets}
  def getDistance():Int = {return distance}
}
