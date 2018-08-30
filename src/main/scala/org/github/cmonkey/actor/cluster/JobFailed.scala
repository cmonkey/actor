package org.github.cmonkey.actor.cluster

/**
 * 任务失败原因
 */
final case class JobFailed(reason: String, job: TransformationJob)
